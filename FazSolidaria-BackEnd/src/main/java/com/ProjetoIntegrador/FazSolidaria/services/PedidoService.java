package com.ProjetoIntegrador.FazSolidaria.services;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoIntegrador.FazSolidaria.exception.PedidoNaoEncontradoException;
import com.ProjetoIntegrador.FazSolidaria.model.Pedido;
import com.ProjetoIntegrador.FazSolidaria.model.Produto;
import com.ProjetoIntegrador.FazSolidaria.model.Usuario;
import com.ProjetoIntegrador.FazSolidaria.repository.PedidoRepository;

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

	public List<Pedido> mostrarPedidosCadastrados() {
		return pedidoRepository.findAll();
	}

	public Pedido buscarIdPedidoOuFalhe(Long idPedido) {
		return pedidoRepository.findById(idPedido).orElseThrow(() -> new PedidoNaoEncontradoException(idPedido));
	}

	@Transactional
	public Pedido emissaoPedido(Pedido pedido) {
		armazenaItensPedido(pedido);
		armazenaUsuarioPedido(pedido);
		return pedidoRepository.save(pedido);
	}

	private void armazenaUsuarioPedido(Pedido pedido) {
		Usuario usuario = cadastroUsuario.buscarIdUsuarioOuFalhe(pedido.getCliente().getId());
		pedido.setCliente(usuario);
	}

	private void armazenaItensPedido(Pedido pedido) {
		pedido.getItensPedido().forEach(item -> {
			Produto produto = cadastroProduto.buscarIdProdutoOuFalhe(item.getProduto().getId());
			item.setProduto(produto);
		});
	}
}
