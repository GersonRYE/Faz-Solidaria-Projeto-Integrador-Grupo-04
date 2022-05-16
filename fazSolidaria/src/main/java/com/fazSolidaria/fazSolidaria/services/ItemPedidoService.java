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
		//Localizando o objeto produto pelo ID
		Produto pegaProduto = produtoService.codigoProduto(itemPedido.getProduto().getId());
		System.out.println(pegaProduto + " Produto");
		//Armazenando o objeto produto encontrado no produto da entity itensPedido
		itemPedido.setProduto(pegaProduto);
		//Armzenando o preço do produto no preco unitário da Entity itensPedido
		itemPedido.precoUnitario();
		//Calculando o preço total e armazenando na variavel preçoTotal da Entity itensPedido
		itemPedido.calcularPrecoTotal();

		//Localizando o objeto pedido pelo ID
		Pedido pedido = pedidoService.buscarCodPedido(itemPedido.getPedido().getId());
		//Armazenando o objeto pedido encontrado no pedido da entity itensPedido
		itemPedido.setPedido(pedido);
		//Armazenando o valor total na entity pedido
		itemPedido.valorTotalPedido();
//		itemPedido.estoqueProduto();
		//Somatoria do valor total e armazena no atributo valorTotal da entity pedido
		pedido.valorTotalPedido();
		System.out.println(pedido.getValorTotal() + " Teste valor na tabela Pedido");
		//Emissao do pedido atualizado na Entity Pedido
		pedidoService.emissaoPedido(pedido);
		

		return repository.save(itemPedido);
	}
}
