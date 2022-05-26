package com.ProjetoIntegrador.FazSolidaria.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoIntegrador.FazSolidaria.model.ItemPedido;
import com.ProjetoIntegrador.FazSolidaria.model.Pedido;

@Service
public class StatusPedidoService {
	
	@Autowired
	PedidoService emitirPedido;
	
	@Autowired
	ItemPedidoService itens;
	
	@Transactional
	public void confirmar(Long id) {
		Pedido pedido = emitirPedido.buscarIdPedidoOuFalhe(id);
		ItemPedido item = itens.buscarIdItemPedidoOuFalhe(id);
		item.estoqueProduto();
		pedido.confirmar();
	}
	
	@Transactional
	public void cancelar(Long id) {
		Pedido pedido = emitirPedido.buscarIdPedidoOuFalhe(id);
		ItemPedido item = itens.buscarIdItemPedidoOuFalhe(id);
		item.retornaEstoque();
		pedido.cancelar();
	}
	
	@Transactional
	public void entregar(Long id) {
		Pedido pedido = emitirPedido.buscarIdPedidoOuFalhe(id);
		pedido.entregar();
	}
}
