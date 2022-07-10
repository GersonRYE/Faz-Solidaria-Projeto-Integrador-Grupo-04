package com.ProjetoIntegrador.FazSolidaria.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "descrição obrigatório")
	@Size(min = 2, max = 50, message = "a descrição deve possuir entre 2 até 50 caracteres")
	private String tipoAlimento;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE) // Uma Categoria para Muitos Produtos
	@JsonIgnoreProperties(value = "categoria", allowSetters = true)
	private List<Produto> produtos;

}
