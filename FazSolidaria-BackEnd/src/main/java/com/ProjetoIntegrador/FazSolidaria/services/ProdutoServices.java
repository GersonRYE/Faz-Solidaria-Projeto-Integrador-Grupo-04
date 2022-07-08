package com.ProjetoIntegrador.FazSolidaria.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoIntegrador.FazSolidaria.exception.ProdutoNaoEncontradoException;
import com.ProjetoIntegrador.FazSolidaria.model.Produto;
import com.ProjetoIntegrador.FazSolidaria.repository.ProdutoRepository;

@Service
public class ProdutoServices {

	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> mostrarProdutosCadastrados() {

		return produtoRepository.findAll();
	}

	public Produto buscarIdProdutoOuFalhe(Long idProduto) {
		return produtoRepository.findById(idProduto).orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto));
	}

	public Produto mostrarProdutoPeloNome(String nome) {
		return produtoRepository.findAllByNomeIgnoreCase(nome).orElseThrow(() -> new ProdutoNaoEncontradoException(
				String.format("Não foi encontrado produto com o nome '%s' na loja FazSolidaria ", nome)));
	}

	public Produto cadastrarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto atualizarCadastroProduto(Produto produtoNovo) {
		Produto produtoAtual = buscarIdProdutoOuFalhe(produtoNovo.getId());
		repassaNovaInfoProduto(produtoNovo, produtoAtual);
		return produtoRepository.save(produtoNovo);
	}

	public void deletarProduto(Long idProduto) {
		buscarIdProdutoOuFalhe(idProduto);
		produtoRepository.deleteById(idProduto);
	}

	public void repassaNovaInfoProduto(Produto origem, Produto destino) {
		BeanUtils.copyProperties(origem, destino);
	}

}
