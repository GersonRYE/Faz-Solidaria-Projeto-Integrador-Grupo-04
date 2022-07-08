package com.ProjetoIntegrador.FazSolidaria.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoIntegrador.FazSolidaria.exception.EnderecoNaoEncontradoException;
import com.ProjetoIntegrador.FazSolidaria.model.Endereco;
import com.ProjetoIntegrador.FazSolidaria.repository.EnderecoRepository;
import com.google.gson.Gson;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public List<Endereco> mostrarEnderecosCadastrados() {
		return enderecoRepository.findAll();
	}

	public Endereco buscarIdEnderecoOuFalhe(Long idEndereco) {
		return enderecoRepository.findById(idEndereco)
				.orElseThrow(() -> new EnderecoNaoEncontradoException(idEndereco));
	}

	public Endereco buscarPeloUf(String uf) {
		return enderecoRepository.findByUfIgnoreCase(uf).orElseThrow(() -> new EnderecoNaoEncontradoException(
				String.format("Não foi encontrado uf '%s' no banco de dados FazSolidaria ", uf)));
	}

	public Endereco buscarPelaLocalidade(String localidade) {
		return enderecoRepository.findByLocalidadeIgnoreCase(localidade)
				.orElseThrow(() -> new EnderecoNaoEncontradoException(String
						.format("Não foi encontrado a localidade '%s' no banco de dados FazSolidaria ", localidade)));
	}

	public Endereco cadastrarEndereco(Endereco endereco) {
		pesquisaEnderecoCep(endereco);
		return enderecoRepository.save(endereco);
	}

	public Endereco atualizarCadastroEndereco(Endereco enderecoNovo) {
		Endereco enderecoAtual = buscarIdEnderecoOuFalhe(enderecoNovo.getId());
		repassaNovaInfoEndereco(enderecoNovo, enderecoAtual);
		return enderecoRepository.save(enderecoNovo);
	}

	public void deletarEndereco(Long idEndereco) {
		buscarIdEnderecoOuFalhe(idEndereco);
		enderecoRepository.deleteById(idEndereco);
	}

	private void repassaNovaInfoEndereco(Endereco origem, Endereco destino) {
		BeanUtils.copyProperties(origem, destino, "numero", "usuario");
	}

	public void pesquisaEnderecoCep(Endereco enderecoCep) {
		try {
			URL url = new URL("https://viacep.com.br/ws/" + enderecoCep.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String cep = "";
			StringBuilder jsonCep = new StringBuilder();
			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}

			Endereco cepEndereco = new Gson().fromJson(jsonCep.toString(), Endereco.class);

			cepEndereco.setCep(cepEndereco.getCep());
			cepEndereco.setLogradouro(cepEndereco.getLogradouro());
			cepEndereco.setBairro(cepEndereco.getBairro());
			cepEndereco.setLocalidade(cepEndereco.getLocalidade());
			cepEndereco.setUf(cepEndereco.getUf());

			repassaNovaInfoEndereco(cepEndereco, enderecoCep);

			BeanUtils.copyProperties(enderecoCep, enderecoCep, "numero", "pais", "usuario");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
