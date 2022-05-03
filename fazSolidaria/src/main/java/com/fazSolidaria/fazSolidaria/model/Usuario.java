package com.fazSolidaria.fazSolidaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@CPF
	@Column(unique = true)
	private String cpf;

	@NotBlank(message = "É obrigatório preencher o nome")
	@Size(min = 3, max = 50, message = "O nome do cliente deve ter de 3 até 50 caracteres")
	
	private String nome;

	@NotNull(message = "O atributo email é Obrigatório!")
	@Email
	@Size(min = 5, max = 100)
	@Column(unique = true)
	private String email;

	@NotBlank(message = "É obrigatório informar senha")
	@Pattern(regexp = "^(?=.*[0-9]) (?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$", message = "A senha deve conter pelo menos um caracter minusculo, um caracter maiúsculo, um número e um caracter especial e deve conter o mínimo de 8 e máximo de 15 caracteres.")
	private String senha;

	@NotBlank(message = "É obrigatório informar data de nascimento")
	@Size(min = 10, max = 10)
	private String dataNasc;

	@NotBlank(message = "É obrigatório informar o telefone")
	private String telefone;

	private String foto;

	@JoinColumn(name = "endereco_id", nullable = false)
	@ManyToOne
	@JsonIgnoreProperties("usuario")
	private Endereco endereco;


}
