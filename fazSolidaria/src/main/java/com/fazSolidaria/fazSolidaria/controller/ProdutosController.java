package com.fazSolidaria.fazSolidaria.controller;

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

import com.fazSolidaria.fazSolidaria.model.Produto;
import com.fazSolidaria.fazSolidaria.services.ProdutoServices;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {

	
	@Autowired
	private ProdutoServices produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> MostrarProdutosCadastrados() {
		return ResponseEntity.ok(produtoService.mostrarProdutosCadastrados());
	}

	@GetMapping(path = "/nome")
	public ResponseEntity<Produto> MostrarProdutoPeloNome(@Valid @RequestParam String nome) {
		return ResponseEntity.ok(produtoService.mostrarProdutoPeloNome(nome));
	}

	@PostMapping
	public ResponseEntity<Produto> CadastrarProduto(@Valid @RequestBody Produto nomeProduto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrarProduto(nomeProduto));
	}
	
//	@PutMapping
//	public ResponseEntity<ProdutoModel> AlterarInformacaoProduto(@Valid @RequestBody ProdutoModel produto){
//		return produtoService.codigoProduto(produto.getId()).map(resp ->{
//			return ResponseEntity.ok().body(produtoService.salvarAlteracao(produto));
//		}).orElse(ResponseEntity.notFound().build());
//	}
	
	@PutMapping
	public ResponseEntity<Produto> AlterarInformação (@Valid @RequestBody Produto produto ){
		Produto alterarProduto = produtoService.codigoProduto(produto.getId());
		//alterarProduto.setNome(produto.getNome());
		produtoService.copiaInfoNovas(produto, alterarProduto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(produtoService.cadastrarProduto(alterarProduto));
	}

//	@DeleteMapping(path = "/{id}")
//	public ResponseEntity<?> DeletarProduto(@Valid @PathVariable long id) {
//		return produtoService.codigoProduto(id).map(resp -> {
//			produtoRepository.deleteById(id);
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//		}).orElse(ResponseEntity.notFound().build());
//	}
	
	@DeleteMapping(path = "/{id}")
	public void DeletarProduto(@Valid @PathVariable long id){
		produtoService.excluir(id);
	}
}
