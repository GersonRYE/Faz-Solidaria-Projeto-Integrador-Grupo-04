package com.fazSolidaria.fazSolidaria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;


@Entity
@Table (name = "tb_cliente")
public class ClienteModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCliente;
	
	@CPF
	private String cpf;
	
	@NotBlank (message = "É obrigatório preencher o nome")
	@Size (min=3, max=50, message = "O nome do cliente deve ter de 3 até 50 caracteres")
	private String nomeCliente;
	
	@Email
	@NotBlank (message = "É obrigatório informar email")
	private String email;
	
	@NotBlank (message = "É obrigatório informar senha")
	@Size (min=8, max=15, message = "A senha deve conter de 3 até 50 caracteres")
	private String senha;
	
	@NotBlank (message = "É obrigatório informar data de nascimento") 
	@Size (min=10, max=10)
	private String dataNasc;
	
	@NotBlank (message = "É obrigatório informar o telefone") 
	private String telefone;
	
	private String fotoPerfil;
	
	
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	


}
