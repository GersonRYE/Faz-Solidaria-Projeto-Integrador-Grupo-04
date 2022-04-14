package com.fazSolidaria.fazSolidaria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_Endereco")
public class EnderecoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEndereco;
	
	
	@NotBlank (message = "É obrigatório preencher o endereço")
	@Size (min=3, max=50, message = "O endereço deve conter de 3 até 50 caracteres")
	private String logradouro;
	
	@NotBlank (message = "É obrigatório preencher o bairro")
	@Size (min=3, max=50, message = "O bairro deve conter de 3 até 50 caracteres")
	private String bairro;
	
	@NotBlank (message = "É obrigatório preencher a cidade")
	@Size (min=3, max=50, message = "A cidade deve conter de 3 até 50 caracteres")
	private String cidade;
	
	@NotBlank (message = "É obrigatório preencher o CEP")
	@Size (min=3, max=50, message = "O CEP deve conter de 3 até 50 caracteres")
	private String cep;
	
	@NotBlank (message = "É obrigatório preencher o País")
	@Size (min=3, max=50, message = "O País deve conter de 3 até 50 caracteres")
	private String pais;
	
	@NotBlank (message = "É obrigatório preencher o complemento")
	@Size (min=3, max=50, message = "O complemento deve conter de 3 até 50 caracteres")
	private String complemento;
	
	@NotBlank (message = "É obrigatório preencher o Estado")
	@Size (min=3, max=50, message = "O Estado deve conter de 3 até 50 caracteres")
	private String estado;
	
	@NotBlank (message = "É obrigatório preencher o númerro")
	@Size (min=1, max=50, message = "O número deve conter de 1 até 50 caracteres")
	private String numero;
	
	public long getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

}
