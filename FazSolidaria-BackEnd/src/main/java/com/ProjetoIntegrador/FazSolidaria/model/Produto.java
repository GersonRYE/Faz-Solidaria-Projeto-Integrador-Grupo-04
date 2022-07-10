package com.ProjetoIntegrador.FazSolidaria.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Nome do produto é obrigatório")
	@Size(min = 3, max = 45, message = "O nome do produto deve conter entre 3 até 45 caracteres")
	private String nome;

	@NotNull(message = "O preço é obrigatório")
	private BigDecimal preco;

	@NotNull(message = "O estoque é obrigatório")
	private Integer estoque;

	@NotBlank(message = "É obrigatório ter imagem")
	private String imagem;
	
	@NotNull
	private String descricao;
	
	private Integer qtd;

	@JoinColumn(name = "categoria_id", nullable = false)
	@ManyToOne
	@JsonIgnoreProperties(value = "produtos", allowSetters = true)
	private Categoria categoria;

	public void moedaDuasCasasDecimais() {
		getPreco().setScale(2, RoundingMode.HALF_UP);
	}

}
