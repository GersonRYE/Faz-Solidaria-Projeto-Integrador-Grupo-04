package com.ProjetoIntegrador.FazSolidaria.exception;

public class ItemPedidoNaoEncontradoException extends NaoEncontradoException {

	private static final long serialVersionUID = 1L;

	public ItemPedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ItemPedidoNaoEncontradoException(Long idItemPedido) {
		this(String.format("Não existe o item pedido com código %d na loja FazSolidaria", idItemPedido));
	}

}
