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
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoIntegrador.FazSolidaria.model.Categoria;
import com.ProjetoIntegrador.FazSolidaria.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> mostrarCategoriasCadastradas() {
		return ResponseEntity.ok(categoriaService.mostrarCategoriasCadastradas());
	}

	@GetMapping("/buscar-id-categoria/{idCategoria}")
	public ResponseEntity<Categoria> buscarIdCategoriaOuFalhe(@Valid @PathVariable long idCategoria) {
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.buscarIdCategoriaOuFalhe(idCategoria));
	}

	@GetMapping("/nome-categoria/{nomeCategoria}")
	public ResponseEntity<Categoria> buscarPeloNomeCategoria(@Valid @PathVariable String nomeCategoria) {
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.buscarPeloNomeOuFalhe(nomeCategoria));
	}

	@PostMapping("/cadastrar-categoria")
	public ResponseEntity<Categoria> cadastrarCategoria(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.cadastrarCategoria(categoria));
	}

	@PutMapping("/atualizar-categoria")
	public ResponseEntity<Categoria> atualizarCategoria(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.body(categoriaService.atualizarCadastroCategoria(categoria));
	}

	@DeleteMapping("/deletar-id-categoria/{idCategoria}")
	public void deletarCategoria(@Valid @PathVariable long idCategoria) {
		categoriaService.deletarCategoria(idCategoria);
	}
}