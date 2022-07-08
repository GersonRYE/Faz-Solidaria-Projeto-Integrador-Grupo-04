package com.ProjetoIntegrador.FazSolidaria.exception;

public class CategoriaNaoEncontradoException extends NaoEncontradoException{

	private static final long serialVersionUID = 1L;

	public CategoriaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CategoriaNaoEncontradoException(Long idCategoria) {
		this(String.format("Não existe a categoria com código '%d' na loja FazSolidaria", idCategoria));
	}


}
