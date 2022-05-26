package com.ProjetoIntegrador.FazSolidaria.exception;

public class PedidoNaoEncontradoException extends NaoEncontradoException{

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public PedidoNaoEncontradoException(Long idPedido) {
		this(String.format("Não existe o pedido com código %d na loja FazSolidaria", idPedido));
	}
}
