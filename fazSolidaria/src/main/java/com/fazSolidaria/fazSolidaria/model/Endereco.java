package com.fazSolidaria.fazSolidaria.model;

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
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "É obrigatório preencher o CEP")
	@Size(min = 3, max = 50, message = "O CEP deve conter de 3 até 50 caracteres")
	private String cep;

	@NotBlank(message = "É obrigatório preencher o endereço")
	@Size(min = 3, max = 50, message = "O endereço deve conter de 3 até 50 caracteres")
	private String logradouro;

	@NotBlank(message = "É obrigatório preencher o complemento")
	@Size(min = 3, max = 50, message = "O complemento deve conter de 3 até 50 caracteres")
	private String complemento;

	@NotBlank(message = "É obrigatório preencher o bairro")
	@Size(min = 3, max = 50, message = "O bairro deve conter de 3 até 50 caracteres")
	private String bairro;

	@NotBlank(message = "É obrigatório preencher a cidade")
	@Size(min = 3, max = 50, message = "A cidade deve conter de 3 até 50 caracteres")
	private String localidade;

	@NotBlank(message = "É obrigatório preencher o Estado")
	@Size(min = 2, max = 50, message = "O Estado deve conter de 3 até 50 caracteres")
	private String uf;

	@NotBlank(message = "É obrigatório preencher o númerro")
	@Size(min = 1, max = 50, message = "O número deve conter de 1 até 50 caracteres")
	private String numero;

	@NotBlank(message = "É obrigatório preencher o País")
	@Size(min = 3, max = 50, message = "O País deve conter de 3 até 50 caracteres")
	private String pais;

	@OneToMany(mappedBy = "endereco", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("endereco")
	private List<Usuario> usuario;

}
