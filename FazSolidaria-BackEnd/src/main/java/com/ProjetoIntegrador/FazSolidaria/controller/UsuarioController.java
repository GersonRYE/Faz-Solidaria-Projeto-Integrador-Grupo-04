package com.ProjetoIntegrador.FazSolidaria.controller;

import java.util.List;
import java.util.Optional;

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

import com.ProjetoIntegrador.FazSolidaria.input.UsuarioLoginInput;
import com.ProjetoIntegrador.FazSolidaria.model.Usuario;
import com.ProjetoIntegrador.FazSolidaria.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
		return ResponseEntity.ok(usuarioService.mostrarTodosUsuarios());
	}

	@GetMapping("/buscar-id-usuario/{id}")
	public ResponseEntity<Usuario> buscarUsuarioPeloId(@PathVariable long id) {
		Usuario buscaUsuario = usuarioService.codigoUsuario(id);
		return ResponseEntity.ok(buscaUsuario);
	}

	@GetMapping("/nome-usuario")
	public ResponseEntity<Usuario> buscarPeloNome(@Valid @RequestParam String nome) {
		return ResponseEntity.ok(usuarioService.buscarNome(nome));
	}

	@PostMapping("/cadastrar-usuario")
	public ResponseEntity<Optional<Usuario>> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
	}

	@PostMapping("/login-usuario")
	public ResponseEntity<UsuarioLoginInput> loginUsuario(@RequestBody Optional<UsuarioLoginInput> usuario) {
		return usuarioService.validarUsuario(usuario).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PutMapping("/atualizar-usuario")
	public ResponseEntity<Optional<Usuario>> alterarCadastroUsuario(@Valid @RequestBody Usuario usuario) {
		Usuario alteraCadastroUsuario = usuarioService.codigoUsuario(usuario.getId());
		usuarioService.copiaInfoNovas(usuario, alteraCadastroUsuario);
		return ResponseEntity.ok().body(usuarioService.atualizarCadastroUsuario(usuario));
	}
}
