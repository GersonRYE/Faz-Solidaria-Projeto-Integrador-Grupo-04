package com.fazSolidaria.fazSolidaria.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazSolidaria.fazSolidaria.model.ItemPedido;
import com.fazSolidaria.fazSolidaria.model.Pedido;

@Service
public class StatusPedidoService {
	
	@Autowired
	PedidoService emitirPedido;
	
	@Autowired
	ItemPedidoService itens;
	
	@Transactional
	public void confirmar(Long id) {
		Pedido pedido = emitirPedido.buscarCodPedido(id);
		ItemPedido item = itens.codigoItemPedido(id);
		item.estoqueProduto();
		pedido.confirmar();
	}
	
	@Transactional
	public void cancelar(Long id) {
		Pedido pedido = emitirPedido.buscarCodPedido(id);
		ItemPedido item = itens.codigoItemPedido(id);
		item.retornaEstoque();
		pedido.cancelar();
	}
	
	@Transactional
	public void entregar(Long id) {
		Pedido pedido = emitirPedido.buscarCodPedido(id);
		pedido.entregar();
	}
}
