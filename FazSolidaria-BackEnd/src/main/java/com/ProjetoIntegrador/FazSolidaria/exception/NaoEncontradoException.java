package com.ProjetoIntegrador.FazSolidaria.exception;

public abstract class NaoEncontradoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public NaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
