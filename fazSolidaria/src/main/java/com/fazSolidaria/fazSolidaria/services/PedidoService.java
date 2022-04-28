package com.fazSolidaria.fazSolidaria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazSolidaria.fazSolidaria.model.PedidoModel;
import com.fazSolidaria.fazSolidaria.model.ProdutoModel;
import com.fazSolidaria.fazSolidaria.model.UsuarioModel;
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
	
	public List<PedidoModel> mostrarTodosPedidos() {
		return pedidoRepository.findAll();
	}

	public PedidoModel buscarCodPedido(Long pedidoId) {
		return pedidoRepository.findById(pedidoId).orElseThrow(() -> new RuntimeException("Não Existe - NOT FOUND"));
	}

	public PedidoModel emissaoPedido(PedidoModel pedido) {
		armazenaUsuarioPedido(pedido);
		armazenaItensPedido(pedido);
		pedido.calcularValorTotal();
		return pedidoRepository.save(pedido);
	}

	private void armazenaUsuarioPedido(PedidoModel pedido) {
		UsuarioModel usuario = cadastroUsuario.codigoUsuario(pedido.getCliente().getId());
		pedido.setCliente(usuario);
	}

	private void armazenaItensPedido(PedidoModel pedido) {
		pedido.getItens().forEach(item -> {
			ProdutoModel produto = cadastroProduto.codigoProduto(item.getProduto().getId());
			item.setPedido(pedido);
			item.setProduto(produto);
			//item.setPrecoUnitario(produto.getPreco());
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
