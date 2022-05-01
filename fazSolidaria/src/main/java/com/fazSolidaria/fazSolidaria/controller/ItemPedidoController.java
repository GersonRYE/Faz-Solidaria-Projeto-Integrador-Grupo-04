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

import com.fazSolidaria.fazSolidaria.model.ItemPedido;
import com.fazSolidaria.fazSolidaria.services.ItemPedidoService;

@RestController
@RequestMapping("/itens-pedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemPedidoController {

	@Autowired
	ItemPedidoService servico;
	
	@GetMapping
	public ResponseEntity<List<ItemPedido>> MostrarTodoItensPedidos(){
		return ResponseEntity.ok().body(servico.mostrarTodosItensPedido());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> BuscarIdItemPedido(@Valid @PathVariable Long id){
		return ResponseEntity.ok().body(servico.codigoItemPedido(id));
	}
	
	@PostMapping
	public ResponseEntity<ItemPedido> CadastrarItemPedido(@Valid @RequestBody ItemPedido item){
		System.out.println(item.getPedido().getValorTotal()+" Classe ItemPedido");
		return ResponseEntity.status(HttpStatus.CREATED).body(servico.cadastrarOuAtualizarItemPedido(item));
	}
}
