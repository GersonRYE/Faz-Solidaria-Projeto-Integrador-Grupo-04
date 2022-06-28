package com.ProjetoIntegrador.FazSolidaria.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "itemPedidoNovo")
public class ItemPedidoNovo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String imagem;
	
	private BigDecimal precoUnitario;
	
	private int quantidade;
	
	private Long produtoId;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	@JsonIgnoreProperties(value = "itensPedido", allowSetters = true)
	private PedidoNovo pedido;
}
