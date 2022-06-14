package com.ProjetoIntegrador.FazSolidaria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoIntegrador.FazSolidaria.model.Produto;
import com.ProjetoIntegrador.FazSolidaria.services.ProdutoServices;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoServices produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> mostrarProdutosCadastrados() {
		return ResponseEntity.ok(produtoService.mostrarProdutosCadastrados());
	}

	@GetMapping("/buscar-id-produto/{idProduto}")
	public ResponseEntity<Produto> buscarIdProdutoOuFalhe(@Valid @PathVariable long idProduto) {
		return ResponseEntity.ok(produtoService.buscarIdProdutoOuFalhe(idProduto));
	}

	@GetMapping("/buscar-nome-produto")
	public ResponseEntity<Produto> mostrarProdutoPeloNome(@Valid @RequestParam String nomeProduto) {
		return ResponseEntity.ok(produtoService.mostrarProdutoPeloNome(nomeProduto));
	}

	@PostMapping("/cadastrar-produto")
	public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody Produto nomeProduto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrarProduto(nomeProduto));
	}

	@PutMapping("/atualizar-produto")
	public ResponseEntity<Produto> atualizarCadastroProduto(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(produtoService.atualizarCadastroProduto(produto));
	}

	@DeleteMapping("/deletar-id-produto/{id}")
	public void deletarProduto(@Valid @PathVariable long id) {
		produtoService.deletarProduto(id);
	}
}
