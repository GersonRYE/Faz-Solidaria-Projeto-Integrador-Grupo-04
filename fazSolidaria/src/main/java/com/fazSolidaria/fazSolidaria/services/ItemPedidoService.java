package com.fazSolidaria.fazSolidaria.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazSolidaria.fazSolidaria.model.ItemPedido;
import com.fazSolidaria.fazSolidaria.model.Pedido;
import com.fazSolidaria.fazSolidaria.model.Produto;
import com.fazSolidaria.fazSolidaria.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository repository;

	@Autowired
	ProdutoServices produtoService;

	@Autowired
	PedidoService pedidoService;

	public List<ItemPedido> mostrarTodosItensPedido() {
		return repository.findAll();
	}

	public ItemPedido codigoItemPedido(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Não Existe! - NOT FOUND"));
	}

	@Transactional
	public ItemPedido cadastrarOuAtualizarItemPedido(ItemPedido itemPedido) {
//	Pedido pegaPedido = pedidoService.buscarCodPedido(itemPedido.getPedido().getId());
		Produto pegaProduto = produtoService.codigoProduto(itemPedido.getProduto().getId());
		itemPedido.setProduto(pegaProduto);
		itemPedido.precoUnitario();
		itemPedido.calcularPrecoTotal();

		Pedido pedido = pedidoService.buscarCodPedido(itemPedido.getPedido().getId());
		itemPedido.setPedido(pedido);
		itemPedido.valorTotalPedido();
		pedido.valorTotalPedido();
		System.out.println(pedido.getValorTotal() + " Teste valor na tabela Pedido");
		pedidoService.emissaoPedido(pedido);
//	
//	pedido.setValorTotal(itemPedido.getPrecoTotal().add(pedido.getValorTotal()));
//		categoria.setProduto(pegaID);
//	categoria.precoUnitario();
//	categoria.calcularPrecoTotal();

		return repository.save(itemPedido);
	}
}
