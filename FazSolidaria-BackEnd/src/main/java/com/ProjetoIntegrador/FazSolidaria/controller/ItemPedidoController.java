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

import com.ProjetoIntegrador.FazSolidaria.model.ItemPedido;
import com.ProjetoIntegrador.FazSolidaria.services.ItemPedidoService;

@RestController
@RequestMapping("/itens-pedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemPedidoController {

	@Autowired
	ItemPedidoService itemPedidoService;

	@GetMapping
	public ResponseEntity<List<ItemPedido>> mostrarItensPedidoCadastrados() {
		return ResponseEntity.ok().body(itemPedidoService.mostrarItensPedidoCadastrados());
	}

	@GetMapping("/buscar-id-item-pedido/{idItemPedido}")
	public ResponseEntity<ItemPedido> buscarIdItemPedidoOuFalhe(@Valid @PathVariable Long idItemPedido) {
		return ResponseEntity.ok().body(itemPedidoService.buscarIdItemPedidoOuFalhe(idItemPedido));
	}

	@PostMapping("/cadastrar-item-pedido")
	public ResponseEntity<ItemPedido> cadastrarItemPedido(@Valid @RequestBody ItemPedido itemPedido) {
		return ResponseEntity.status(HttpStatus.CREATED).body(itemPedidoService.cadastrarItemPedido(itemPedido));
	}

	@PutMapping("/atualizar-item-pedido")
	public ResponseEntity<ItemPedido> atualizarCadastroItemPedido(@Valid @RequestBody ItemPedido itemPedido) {
		return ResponseEntity.status(HttpStatus.OK).body(itemPedidoService.atualizarCadastroItemPedido(itemPedido));
	}

	@DeleteMapping("/deletar-id-item-pedido/{idItemPedido}")
	public void deletarEndereco(@Valid @PathVariable Long idItemPedido) {
		itemPedidoService.deletarItemPedido(idItemPedido);
	}
}
