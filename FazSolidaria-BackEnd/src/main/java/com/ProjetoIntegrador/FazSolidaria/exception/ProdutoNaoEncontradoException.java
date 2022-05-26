package com.ProjetoIntegrador.FazSolidaria.exception;

public class ProdutoNaoEncontradoException extends NaoEncontradoException {

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ProdutoNaoEncontradoException(Long idProduto) {
		this(String.format("Não existe o produto com código %d na loja FazSolidaria", idProduto));
	}
}
