package com.ProjetoIntegrador.FazSolidaria.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	@CPF(message = "CPF inválido")
	private String cpf;

	@NotBlank(message = "O nome é obrigatório")
	@Size(min = 3, max = 50, message = "O nome do cliente deve conter entre 3 até 50 caracteres")
	private String nome;

	
	@Column(unique = true)
	@Schema(example = "email@email.com.br")
	@NotBlank(message = "O email é Obrigatório!")
	@Email(message = "Email com formato invalido")
	@Size(min = 5, max = 100)
	private String usuario;

	@NotNull
//	@Pattern(regexp = "^(?=.*[0-9]) (?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$", message = "A senha deve conter pelo menos um caracter minusculo, um caracter maiúsculo, um número e um caracter especial e deve conter o mínimo de 8 e máximo de 15 caracteres.")
	private String senha;

	@NotBlank(message = "É obrigatório informar data de nascimento")
	@Size(min = 10, max = 10)
	private String dataNasc;

	@NotBlank(message = "É obrigatório informar o telefone")
	private String telefone;

	private String foto;
	
	private String tipo;

	
//	@JoinColumn(name = "endereco_id", nullable = false)
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Endereco> endereco;
	


	public Usuario(long id, String cpf, String nome, String usuario, String senha, String dataNasc, String telefone) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.dataNasc = dataNasc;
		this.telefone = telefone;
	}
	
	public Usuario() {
		
	}
	
}
