package com.ProjetoIntegrador.FazSolidaria.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoIntegrador.FazSolidaria.model.Pedido;
import com.ProjetoIntegrador.FazSolidaria.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> mostrarPedidos() {
		return ResponseEntity.ok(pedidoService.mostrarTodosPedidos());
	}

	@GetMapping("/buscar-id-pedido/{idPedido}")
	public ResponseEntity<Pedido> buscarIdPedidoOuFalhe(@PathVariable Long idPedido) {
		return ResponseEntity.ok(pedidoService.buscarIdPedidoOuFalhe(idPedido));
	}

	@PostMapping("/cadastrar-pedido")
	public ResponseEntity<Pedido> criarPedido(@Valid @RequestBody Pedido pedido) {
		pedido.setValorTotal(BigDecimal.ZERO);
		pedido.setSubtotal(BigDecimal.ZERO);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.emissaoPedido(pedido));
	}
}
