package com.fazSolidaria.fazSolidaria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_item_pedido")
public class CarrinhoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idItem;
	
	@NotBlank(message = "A quantidade é obrigatório")
	private int quantidade;
	
	
	public long getIdItem() {
		return idItem;
	}
	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
