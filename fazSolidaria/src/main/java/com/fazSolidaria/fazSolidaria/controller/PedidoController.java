package com.fazSolidaria.fazSolidaria.controller;

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

import com.fazSolidaria.fazSolidaria.model.Pedido;
import com.fazSolidaria.fazSolidaria.services.PedidoService;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> MostrarPedidos() {
		return ResponseEntity.ok(pedidoService.mostrarTodosPedidos());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Pedido> MostarPeloCodPedido(@PathVariable Long id) {
		return ResponseEntity.ok(pedidoService.buscarCodPedido(id));
	}

	@PostMapping
	public ResponseEntity<Pedido> criarPedido(@Valid @RequestBody Pedido pedido) {
		pedido.setValorTotal(BigDecimal.ZERO);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.emissaoPedido(pedido));
	}
}
