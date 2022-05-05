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
import com.fazSolidaria.fazSolidaria.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<Endereco>> MostrarTodosEnderecos() {
		return ResponseEntity.ok(enderecoService.mostrarTodosEnderecos());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Endereco> BuscarCodigoEndereco(@PathVariable long id) {
		return ResponseEntity.ok(enderecoService.buscarCodEndereco(id));
	}

	@GetMapping(path = "/uf")
	public ResponseEntity<Endereco> BuscarPeloUf(@RequestParam String uf) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.buscarPeloUf(uf));
	}

	@GetMapping(path = "/localidade")
	public ResponseEntity<Endereco> BuscarPelaLocalidade(@RequestParam String localidade) {
		return ResponseEntity.ok(enderecoService.buscarPelaLocalidade(localidade));
	}

	@GetMapping(path = "/pais")
	public ResponseEntity<Endereco> BuscarPeloPais(@RequestParam String pais) {
		return ResponseEntity.ok(enderecoService.buscarPeloPais(pais));
	}

	@PostMapping
	public ResponseEntity<Endereco> post(@Valid @RequestBody Endereco endereco) {
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.cadastrar(endereco));
	}

	@PutMapping
	public ResponseEntity<Endereco> AtualizarCadastro(@Valid @RequestBody Endereco enderco) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.atualizarCadastro(enderco));
	}

	@DeleteMapping(path = "/{id}")
	public void DeletarEndereco(@Valid @PathVariable Long id) {
		enderecoService.deletarEndereco(id);
	}

}
