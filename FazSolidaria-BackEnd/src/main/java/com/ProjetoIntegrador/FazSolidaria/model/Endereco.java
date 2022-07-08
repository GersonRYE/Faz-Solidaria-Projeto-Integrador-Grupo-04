package com.ProjetoIntegrador.FazSolidaria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "É obrigatório preencher o CEP")
	@Size(min = 3, max = 50, message = "O CEP deve possuir entre 3 até 50 caracteres")
	private String cep;

	private String logradouro;

	private String bairro;

	private String localidade;

	private String uf;

	@NotBlank(message = "É obrigatório preencher o númerro")
	@Size(min = 1, max = 50, message = "O número deve conter de 1 até 50 caracteres")
	private String numero;

	@ManyToOne
	@JsonIgnoreProperties(value = "endereco", allowSetters = true)
	private UsuarioNovo usuario;

}
