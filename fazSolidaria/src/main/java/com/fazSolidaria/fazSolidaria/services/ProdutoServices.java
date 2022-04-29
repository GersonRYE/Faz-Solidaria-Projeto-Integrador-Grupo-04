package com.fazSolidaria.fazSolidaria.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazSolidaria.fazSolidaria.model.Produto;
import com.fazSolidaria.fazSolidaria.repository.ProdutoRepository;

@Service
public class ProdutoServices {

	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> mostrarProdutosCadastrados() {
		return produtoRepository.findAll();
	}

	public Produto mostrarProdutoPeloNome(String nome) {
		return produtoRepository.findAllByNomeIgnoreCase(nome);
	}

	public Produto cadastrarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

//	public ProdutoModel salvarAlteracao(ProdutoModel produto) {
//		return produtoRepository.save(produto);
//	}

	public Produto codigoProduto(Long produto) {
		return produtoRepository.findById(produto).orElseThrow(() -> new RuntimeException("Não Existe - NOT FOUND"));
	}

	public void excluir(Long id) {
		codigoProduto(id);
		produtoRepository.deleteById(id);
	}

	// origem-destino do objeto
	public void copiaInfoNovas(Produto proOrigem, Produto proDestino) {
		BeanUtils.copyProperties(proOrigem, proDestino);
	}

}
