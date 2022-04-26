package com.fazSolidaria.fazSolidaria.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_produto")
public class ProdutoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Nome do produto é obrigatório")
	@Size(min = 3, max = 45, message = "O nome produto deve ter de 3 até 45 caracteres")
	private String nome;

	@NotNull(message = "O preço é obrigatório")
	private BigDecimal preco;

	@NotNull(message = "Informar o estoque é obrigatório")
	private int estoque;

	@NotBlank(message = "É obrigatório ter imagem")
	private String imagem;

	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private CategoriaModel categoria;
	
	public void moedaDuasCasasDecimais() {
		getPreco().setScale(2, RoundingMode.HALF_UP);
	}

}
