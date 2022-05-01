package com.fazSolidaria.fazSolidaria.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazSolidaria.fazSolidaria.model.Pedido;
import com.fazSolidaria.fazSolidaria.model.Produto;
import com.fazSolidaria.fazSolidaria.model.Usuario;
import com.fazSolidaria.fazSolidaria.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoServices cadastroProduto;

	@Autowired
	UsuarioService cadastroUsuario;

	@Autowired
	PedidoRepository pedidoServico;


	public List<Pedido> mostrarTodosPedidos() {
		return pedidoRepository.findAll();
	}

	public Pedido buscarCodPedido(Long pedidoId) {
		return pedidoRepository.findById(pedidoId).orElseThrow(() -> new RuntimeException("Não Existe - NOT FOUND"));
	}

	@Transactional
	public Pedido emissaoPedido(Pedido pedido) {
		armazenaItensPedido(pedido);
		armazenaUsuarioPedido(pedido);
//		armazenaItensPedido(pedido);
//		pedido.calcularValorTotal();
//		pedido.valorTotalPedido();
//		pedido.setValorTotal(itemPedido.getPrecoTotal().add(pedido.getValorTotal()));
		return pedidoRepository.save(pedido);
	}

	private void armazenaUsuarioPedido(Pedido pedido) {
		Usuario usuario = cadastroUsuario.codigoUsuario(pedido.getCliente().getId());
		pedido.setCliente(usuario);
	}

	private void armazenaItensPedido(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = cadastroProduto.codigoProduto(item.getProduto().getId());
			// item.setPedido(pedido);
			item.setProduto(produto);
			// item.setPrecoUnitario(produto.getPreco());
		});
	}

//	@Transactional
//	public Pedido emitir(Pedido pedido) {
//		validarPedido(pedido);
//		validarItens(pedido);
//
//		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
//		pedido.calcularValorTotal();
//
//		return pedidoRepository.save(pedido);
//	}

//	private void validarItens(Pedido pedido) {
//	    pedido.getItens().forEach(item -> {
//	        Produto produto = cadastroProduto.buscarOuFalhar(
//	                pedido.getRestaurante().getId(), item.getProduto().getId());
//	        item.setPedido(pedido);
//	        item.setProduto(produto);
//	        item.setPrecoUnitario(produto.getPreco());
//	    });
}
