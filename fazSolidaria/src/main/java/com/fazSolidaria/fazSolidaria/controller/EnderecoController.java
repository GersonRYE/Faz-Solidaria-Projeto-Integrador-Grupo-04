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

import com.fazSolidaria.fazSolidaria.model.Endereco;
import com.fazSolidaria.fazSolidaria.repository.EnderecoRepository;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class EnderecoController {
	
	@Autowired
	 private EnderecoRepository repository;

	@GetMapping
	public ResponseEntity<List<Endereco>>GetAll(){
		return ResponseEntity.ok(repository.findAll());
	
	}
	@GetMapping (path = "/{id}")
	public ResponseEntity <Endereco>GetById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	@GetMapping (path = "/uf")
	public ResponseEntity<List<Endereco>>GettAllUf(@RequestParam String uf){
		return ResponseEntity.ok(repository.findAllByUfContainingIgnoreCase(uf));
	}
	@GetMapping (path = "/pais")
	public ResponseEntity<List<Endereco>>GettAllPais(@RequestParam String pais){
		return ResponseEntity.ok(repository.findAllByPaisContainingIgnoreCase(pais));
	}
	@PostMapping 
	public ResponseEntity<Endereco> post(@RequestBody Endereco endereco){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(endereco));
	}
	@PutMapping
	public ResponseEntity<Endereco> put(@Valid @RequestBody Endereco endereco){
		return repository.findById(endereco.getId()).map(resp -> ResponseEntity.status(HttpStatus.OK).body(repository.save(endereco))).orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	@DeleteMapping (path = "/{id}")
	public ResponseEntity <?> delete(@PathVariable long id){
		return repository.findById(id).map(resp -> {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
}
