package com.fazSolidaria.fazSolidaria.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedidoModel {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal precoUnitario;

	private BigDecimal precoTotal;

	private Integer quantidade;

	@ManyToOne
	private PedidoModel pedido;

	@ManyToOne
	private ProdutoModel produto;
	
	public void calcularPrecoTotal() {
		BigDecimal precoUnitario = this.getPrecoUnitario();
		Integer quantidade = this.getQuantidade();
		if (precoUnitario == null) {
			precoUnitario = BigDecimal.ZERO;
		}
		if (quantidade == null) {
			quantidade = 0;
		}
		this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
	}
}
