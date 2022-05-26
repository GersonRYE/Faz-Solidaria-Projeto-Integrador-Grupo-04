package com.ProjetoIntegrador.FazSolidaria.exceptionHandler;

import lombok.Getter;

@Getter
public enum TipoProblema {
	MENSAGEM_CORPO_INCOMPREENSIVEL("/mensagem-corpo-incompreensivel", "Mensagem corpo incompreensivel"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Erro de negócio"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	ERRO_DE_SISTEM("/erro-de-sistema", "Erro de sistema"),
	DADOS_INVALIDOS("/dados-invalido", "Dados inválidos");

	private String uri;
	private String title;

	TipoProblema(String path, String title) {
		this.uri = "https://fazSolidaria.com.br" + path;
		this.title = title;
	}

}
