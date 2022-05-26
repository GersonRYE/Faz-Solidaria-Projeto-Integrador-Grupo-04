package com.ProjetoIntegrador.FazSolidaria.exceptionHandler;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problema {

	private Integer status;
	private String tipo;
	private String titulo;
	private String detalhe;
	
	private String mensagemUsuario;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;
	private List<Objeto>objetos;
	
	
	@Getter
	@Builder
	public static class Objeto{
		private String nome;
		private String mensagemUsuario;
	}
}
