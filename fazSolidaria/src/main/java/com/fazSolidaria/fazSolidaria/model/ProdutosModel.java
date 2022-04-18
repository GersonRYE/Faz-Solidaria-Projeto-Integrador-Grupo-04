package com.fazSolidaria.fazSolidaria.model;

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

@Entity
@Table(name = "tb_produtos")

public class ProdutosModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank (message = "Nome do produto é obrigatório" )
	@Size (min=3, max=45, message = "O nome produto deve ter de 3 até 45 caracteres")
	private String nomeProduto;
	
	@NotNull (message = "O preço é obrigatório")
	private double precoProduto;
	
	@NotNull (message = "Informar o estoque é obrigatório")
	private int estoqueProduto;
	
	@NotBlank (message = "É obrigatório ter imagem")
	private String imagemProduto;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private CategoriaModel categoria;
	
	
	public CategoriaModel getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public double getPrecoProduto() {
		return precoProduto;
	}
	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}
	public int getEstoqueProduto() {
		return estoqueProduto;
	}
	public void setEstoqueProduto(int estoqueProduto) {
		this.estoqueProduto = estoqueProduto;
	}
	public String getImagemProduto() {
		return imagemProduto;
	}
	public void setImagemProduto(String imagemProduto) {
		this.imagemProduto = imagemProduto;
	}
	
	
	

}
