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

import com.fazSolidaria.fazSolidaria.model.ItemPedidoModel;
import com.fazSolidaria.fazSolidaria.services.ItemPedidoService;

@RestController
@RequestMapping("/itens-pedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemPedidoController {

	@Autowired
	ItemPedidoService servico;
	
	@GetMapping
	public ResponseEntity<List<ItemPedidoModel>> MostrarTodoItensPedidos(){
		return ResponseEntity.ok().body(servico.mostrarTodosItensPedido());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedidoModel> BuscarIdItemPedido(@Valid @PathVariable Long id){
		return ResponseEntity.ok().body(servico.codigoItemPedido(id));
	}
	
	@PostMapping
	public ResponseEntity<ItemPedidoModel> CadastrarItemPedido(@Valid @RequestBody ItemPedidoModel item){
		System.out.println(item.getProduto().getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(servico.cadastrarOuAtualizarCategoria(item));
	}
}
