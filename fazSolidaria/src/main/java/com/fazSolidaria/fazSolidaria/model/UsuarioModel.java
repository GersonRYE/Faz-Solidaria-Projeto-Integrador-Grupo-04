package com.fazSolidaria.fazSolidaria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_cliente")
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@CPF
	private String cpf;

	@NotBlank(message = "É obrigatório preencher o nome")
	@Size(min = 3, max = 50, message = "O nome do cliente deve ter de 3 até 50 caracteres")
	private String nome;

	@Schema(example = "email@email.com")
	@NotNull(message = "O atributo usurio é obrigatório")
	@Email(message = "O atributo usurio deve ser um e-mail válido")
	private String usuario;

	@NotBlank(message = "É obrigatório informar senha")
	@Size(min = 8, max = 15, message = "A senha deve conter de 3 até 50 caracteres")
	private String senha;

	@NotBlank(message = "É obrigatório informar data de nascimento")
	@Size(min = 10, max = 10)
	private String dataNasc;

	@NotBlank(message = "É obrigatório informar o telefone")
	private String telefone;

	private String foto;
	
	@ManyToOne
	@JsonIgnoreProperties("usuarios")
	private EnderecoModel endereco;
	

	public UsuarioModel(long id, String cpf, String nome, String usuario, String senha, String dataNasc, String telefone, String foto) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.dataNasc = dataNasc;
		this.telefone = telefone;
		this.foto = foto;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsusario(String email) {
		this.usuario = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public EnderecoModel getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}

}
