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

import com.fazSolidaria.fazSolidaria.model.Categoria;
import com.fazSolidaria.fazSolidaria.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> MostrarTodasCategorias() {
		return ResponseEntity.ok(categoriaService.mostrarTodasCategorias());
	}
	
//	@GetMapping(path = "/{id}")
//	public ResponseEntity<CategoriaModel> GetById(@PathVariable long id) {
//		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
//	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Categoria> BuscarPeloCodProduto (@PathVariable long id){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.codigoCategoria(id));
	}

	@GetMapping(path = "/categoria")
	public ResponseEntity<Categoria> BuscarCategoriaNome(@RequestParam String nome) {
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.buscarPeloNome(nome));
	}

	@PostMapping
	public ResponseEntity<Categoria> CadastrarCategoria(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.cadastrarOuAtualizarCategoria(categoria));
	}

	@PutMapping
	public ResponseEntity<Categoria> AtualizarCategoria(@Valid @RequestBody Categoria categoria) {
		Categoria alterarCategoria = categoriaService.codigoCategoria(categoria.getId());
		categoriaService.atualizaInformacao(categoria, alterarCategoria);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(categoriaService.cadastrarOuAtualizarCategoria(categoria));
	}

	@DeleteMapping(path = "/{id}")
	public void DeletarCategoria(@Valid @PathVariable long id) {
		categoriaService.deletarCategoria(id);
	}
}