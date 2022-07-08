package com.ProjetoIntegrador.FazSolidaria.model;

import lombok.Data;

@Data
public class UsuarioLoginInput {
	private long id;

	private String nome;

	private String usuario;

	private String foto;

	private String senha;

	private String token;
	
	private String tipo;
}
