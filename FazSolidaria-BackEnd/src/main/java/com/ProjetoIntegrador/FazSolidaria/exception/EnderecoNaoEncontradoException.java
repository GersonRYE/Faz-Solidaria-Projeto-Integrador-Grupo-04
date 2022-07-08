package com.ProjetoIntegrador.FazSolidaria.exception;

public class EnderecoNaoEncontradoException  extends NaoEncontradoException{

	private static final long serialVersionUID = 1L;

	public EnderecoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public EnderecoNaoEncontradoException(Long idEndereco) {
		this(String.format("Não existe o endereço com código %d na loja FazSolidaria", idEndereco));
	}
	
}
