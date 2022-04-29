package com.fazSolidaria.fazSolidaria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazSolidaria.fazSolidaria.model.ItemPedido;
import com.fazSolidaria.fazSolidaria.model.Produto;
import com.fazSolidaria.fazSolidaria.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository repository;
	
	@Autowired
	ProdutoServices produtoService ;
	
	public List<ItemPedido> mostrarTodosItensPedido () {
		return repository.findAll();
	}
	
	public ItemPedido codigoItemPedido(Long id) {
		return repository.findById(id).orElseThrow(()-> new RuntimeException("Não Existe! - NOT FOUND"));
	}
	
	
	public ItemPedido cadastrarOuAtualizarCategoria(ItemPedido itemPedido) {
	Produto pegaProduto = produtoService.codigoProduto(itemPedido.getProduto().getId());
	itemPedido.setProduto(pegaProduto);
	itemPedido.precoUnitario();
	itemPedido.calcularPrecoTotal();
//		categoria.setProduto(pegaID);
//	categoria.precoUnitario();
//	categoria.calcularPrecoTotal();
		
		return repository.save(itemPedido);
	}
}
