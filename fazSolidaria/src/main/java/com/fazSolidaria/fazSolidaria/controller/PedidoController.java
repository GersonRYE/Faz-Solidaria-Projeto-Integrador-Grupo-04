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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fazSolidaria.fazSolidaria.model.PedidoModel;
import com.fazSolidaria.fazSolidaria.services.PedidoService;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<PedidoModel>> MostrarPedidos() {
		return ResponseEntity.ok(pedidoService.mostrarTodosPedidos());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<PedidoModel> MostarPeloCodPedido(@PathVariable Long id) {
		return ResponseEntity.ok(pedidoService.buscarCodPedido(id));
	}

	@PostMapping
	public ResponseEntity<PedidoModel> criarPedido(@Valid @RequestBody PedidoModel pedido) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.emissaoPedido(pedido));
	}
}
