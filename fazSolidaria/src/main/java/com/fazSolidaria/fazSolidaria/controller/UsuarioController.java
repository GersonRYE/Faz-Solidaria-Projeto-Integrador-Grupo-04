package com.fazSolidaria.fazSolidaria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fazSolidaria.fazSolidaria.model.UsuarioModel;
import com.fazSolidaria.fazSolidaria.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioModel>> GetAll() {
		return ResponseEntity.ok(usuarioService.mostrarTodosUsuarios());
	}

//	@GetMapping(path = "/{id}")
//	public ResponseEntity<UsuarioModel> GetById(@PathVariable long id) {
//		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
//	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<UsuarioModel> BuscarUsuarioPeloId(@PathVariable long id) {
		UsuarioModel buscaUsuario = usuarioService.codigoUsuario(id);
		return ResponseEntity.ok(buscaUsuario);
	}

	@GetMapping(path = "/nome")
	public ResponseEntity<UsuarioModel> getByNome(@Valid @RequestParam String nome) {
		return ResponseEntity.ok(usuarioService.buscarNome(nome));
	}

	@PostMapping
	public ResponseEntity<UsuarioModel> post(@Valid @RequestBody UsuarioModel usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
	}

//	@PutMapping
//	public ResponseEntity<UsuarioModel> put(@Valid @RequestBody UsuarioModel usuario) {
//		return repository.findById(usuario.getId()).map(resp -> {
//			return ResponseEntity.ok().body(repository.save(usuario));
//		}).orElse(ResponseEntity.notFound().build());
//	}

	@PutMapping
	public ResponseEntity<UsuarioModel> AlterarCadastro(@Valid @RequestBody UsuarioModel usuario) {
		UsuarioModel alteraCadastroUsuario = usuarioService.codigoUsuario(usuario.getId());
		usuarioService.copiaInfoNovas(usuario, alteraCadastroUsuario);
		return ResponseEntity.ok().body(usuarioService.atualizarCadastro(alteraCadastroUsuario));
	}
}
