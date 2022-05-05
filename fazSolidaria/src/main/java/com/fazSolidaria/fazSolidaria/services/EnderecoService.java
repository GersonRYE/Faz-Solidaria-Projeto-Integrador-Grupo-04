package com.fazSolidaria.fazSolidaria.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazSolidaria.fazSolidaria.model.Endereco;
import com.fazSolidaria.fazSolidaria.repository.EnderecoRepository;
import com.google.gson.Gson;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	public List<Endereco> mostrarTodosEnderecos(){
		return enderecoRepository.findAll();
	}
	
	public Endereco buscarPeloUf(String uf) {
		return enderecoRepository.findByUfIgnoreCase(uf);
	}
	
	public Endereco buscarPelaLocalidade(String localidade) {
		return enderecoRepository.findByLocalidadeIgnoreCase(localidade);
	}
	
	public Endereco buscarPeloPais(String pais) {
		return enderecoRepository.findByPaisIgnoreCase(pais);
	}
	
	public Endereco buscarCodEndereco(Long id) {
		return enderecoRepository.findById(id).orElseThrow(()-> new RuntimeException("Não Existe - NOT FOUND"));
	}
	
	public Endereco cadastrar(Endereco endereco) {
		pesquisaEnderecoCep(endereco);
		return enderecoRepository.save(endereco);
	}
	
	public Endereco atualizarCadastro(Endereco endereco) {
		Endereco alteraEndereco = buscarCodEndereco(endereco.getId());
		repassaNovaInfoEndereco(endereco, alteraEndereco);
		return enderecoRepository.save(endereco);
	}
	
	public void deletarEndereco(Long id) {
		buscarCodEndereco(id);
		enderecoRepository.deleteById(id);
	}
	
	public void repassaNovaInfoEndereco(Endereco origem, Endereco destino) {
		BeanUtils.copyProperties(origem, destino, "numero", "pais", "usuario");
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
			cepEndereco.setComplemento(cepEndereco.getComplemento());
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
