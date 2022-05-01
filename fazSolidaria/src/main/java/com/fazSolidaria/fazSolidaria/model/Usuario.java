package com.fazSolidaria.fazSolidaria.model;



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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@CPF
	private String cpf;

	@NotBlank(message = "É obrigatório preencher o nome")
	@Size(min = 3, max = 50, message = "O nome do cliente deve ter de 3 até 50 caracteres")
	private String nome;

	@NotNull(message = "O atributo email é Obrigatório!")
	@Email
	@Size(min = 5, max = 100)
	private String email;

	@NotBlank(message = "É obrigatório informar senha")
	@Size(min = 8, max = 15, message = "A senha deve conter de 3 até 50 caracteres")
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

	public boolean senhaValida(String senha) {

		String regex = "^(?=.*[0-9])" + "(?=.[a-z])(?=.[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,15}$";

		Pattern p = Pattern.compile(regex);

		if (senha == null) {
			return false;
		}

		Matcher m = p.matcher(senha);

		if (m.matches() == true) {
			this.senha = senha;
		}

		return m.matches();

	}
}