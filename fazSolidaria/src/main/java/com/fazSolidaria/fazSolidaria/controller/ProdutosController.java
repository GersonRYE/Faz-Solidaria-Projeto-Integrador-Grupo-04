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

import com.fazSolidaria.fazSolidaria.model.ProdutosModel;
import com.fazSolidaria.fazSolidaria.repository.ProdutosRepository;

@RestController
@RequestMapping(path = "/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {

	@Autowired
	private ProdutosRepository repository;

	@GetMapping
	public ResponseEntity<List<ProdutosModel>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ProdutosModel> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = "/nome")
	public ResponseEntity<List<ProdutosModel>> getByNomeProduto(@Valid @RequestParam String nomeProduto) {
		return ResponseEntity.ok(repository.findAllByNomeProdutoContainigIgnoreCase(nomeProduto));
	}

	@PostMapping
	public ResponseEntity<ProdutosModel> post(@Valid @RequestBody ProdutosModel nomeProduto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(nomeProduto));
	}

	@PutMapping
	public ResponseEntity<ProdutosModel> put(@Valid @RequestBody ProdutosModel nomeProduto) {
		return repository.findById(nomeProduto.getId()).map(resp -> {
			return ResponseEntity.ok().body(repository.save(nomeProduto));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@Valid @PathVariable long id) {
		return repository.findById(id).map(resp -> {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
