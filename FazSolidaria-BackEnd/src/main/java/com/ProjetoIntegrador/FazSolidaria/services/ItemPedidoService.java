package com.ProjetoIntegrador.FazSolidaria.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoIntegrador.FazSolidaria.exception.ItemPedidoNaoEncontradoException;
import com.ProjetoIntegrador.FazSolidaria.model.ItemPedido;
import com.ProjetoIntegrador.FazSolidaria.model.Pedido;
import com.ProjetoIntegrador.FazSolidaria.model.Produto;
import com.ProjetoIntegrador.FazSolidaria.repository.ItemPedidoRepository;
import com.ProjetoIntegrador.FazSolidaria.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Autowired
	ProdutoServices produtoService;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	ProdutoRepository produto;

	public List<ItemPedido> mostrarTodosItensPedido() {
		return itemPedidoRepository.findAll();
	}

	public ItemPedido buscarIdItemPedidoOuFalhe(Long id) {
		return itemPedidoRepository.findById(id).orElseThrow(() -> new ItemPedidoNaoEncontradoException(id));
	}

	@Transactional
	public ItemPedido cadastrarItemPedido(ItemPedido itemPedido) {
		// Localizando o objeto produto pelo ID
		Produto pegaProduto = produtoService.buscarIdProdutoOuFalhe(itemPedido.getProduto().getId());

		// Armazenando o objeto produto encontrado no produto da entity itensPedido
		itemPedido.setProduto(pegaProduto);

//		Optional<Produto> produtoExiste = produto.findAllByNomeIgnoreCase(pegaProduto.getNome());
//		if (produtoExiste.isPresent()) {
//			throw new EntidadeEmUsoException(
//					String.format("Ja esta em uso o produto '%s' adicione outro produto ou atualize a quantidade",
//							itemPedido.getProduto().getNome()));
//		}
		// Armzenando o preço do produto no preco unitário da Entity itensPedido
		itemPedido.precoUnitario();
		// Calculando o preço total e armazenando na variavel preçoTotal da Entity
		// itensPedido
		itemPedido.calcularPrecoTotal();

		// Localizando o objeto pedido pelo ID
		Pedido pedido = pedidoService.buscarIdPedidoOuFalhe(itemPedido.getPedido().getId());
		// Armazenando o objeto pedido encontrado no pedido da entity itensPedido
		itemPedido.setPedido(pedido);
		// Armazenando o valor total na entity pedido
		itemPedido.valorTotalPedido();
		// Somatoria do valor total e armazena no atributo valorTotal da entity pedido
		pedido.valorTotalPedido();

		// Emissao do pedido atualizado na Entity Pedido
		pedidoService.emissaoPedido(pedido);

		return itemPedidoRepository.save(itemPedido);
	}
	
	public void deletarItemPedido(Long idItemPedido) {
		buscarIdItemPedidoOuFalhe(idItemPedido);
		itemPedidoRepository.deleteById(idItemPedido);
	}
	
	public ItemPedido atualizarCadastroItemPedido(ItemPedido itemNovo) {
		ItemPedido itemAtual = buscarIdItemPedidoOuFalhe(itemNovo.getId());
		repassaNovaInfoItemPedido(itemNovo, itemAtual);
		return itemPedidoRepository.save(itemNovo);
	}
	
	private void repassaNovaInfoItemPedido(ItemPedido origem, ItemPedido destino) {
		BeanUtils.copyProperties(origem, destino);
	}
}
