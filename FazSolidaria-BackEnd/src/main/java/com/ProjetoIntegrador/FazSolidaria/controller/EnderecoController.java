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

import com.ProjetoIntegrador.FazSolidaria.model.Endereco;
import com.ProjetoIntegrador.FazSolidaria.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<Endereco>> mostrarTodosEnderecos() {
		return ResponseEntity.ok(enderecoService.mostrarTodosEnderecos());
	}

	@GetMapping("/buscar-id-endereco/{idEndereco}")
	public ResponseEntity<Endereco> buscarCodigoEndereco(@Valid @PathVariable long idEndereco) {
		return ResponseEntity.ok(enderecoService.buscarIdEnderecoOuFalhe(idEndereco));
	}

	@GetMapping("/endereco-uf")
	public ResponseEntity<Endereco> buscarPeloUf(@Valid @RequestParam String uf) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.buscarPeloUf(uf));
	}

	@GetMapping("/endereco-localidade")
	public ResponseEntity<Endereco> buscarPelaLocalidade(@Valid @RequestParam String localidade) {
		return ResponseEntity.ok(enderecoService.buscarPelaLocalidade(localidade));
	}

	@PostMapping("/cadastrar-endereco")
	public ResponseEntity<Endereco> cadastrarEndereco(@Valid @RequestBody Endereco endereco) {
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.cadastrarEndereco(endereco));
	}

	@PutMapping("/atualizar-endereco")
	public ResponseEntity<Endereco> atualizarCadastro(@Valid @RequestBody Endereco enderco) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.atualizarCadastroEndereco(enderco));
	}

	@DeleteMapping("/deletar-id-endereco/{idEndereco}")
	public void deletarEndereco(@Valid @PathVariable Long idEndereco) {
		enderecoService.deletarEndereco(idEndereco);
	}

}
