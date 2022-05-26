package com.ProjetoIntegrador.FazSolidaria.exception;

public class UsuarioNaoEncontradoException extends NaoEncontradoException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(Long idUsuario) {
		this(String.format("Não existe o usuário com código %d na loja FazSolidaria", idUsuario));
	}

}
