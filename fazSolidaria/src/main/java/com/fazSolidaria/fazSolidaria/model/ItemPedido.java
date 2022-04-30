package com.fazSolidaria.fazSolidaria.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "itemPedido")
public class ItemPedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(nullable = false, name = "pedido_id")
	@ManyToOne // Um item pedido para muitos pedidos
	private Pedido pedido;

	@JoinColumn(nullable = false, name = "produto_id")
	@ManyToOne // Um item pedido para muitos produtos
	private Produto produto;

	private BigDecimal precoUnitario;

	private BigDecimal precoTotal;

	private Integer quantidade;

	public void precoUnitario() {
		setPrecoUnitario(getProduto().getPreco());
	}

	public void calcularPrecoTotal() {
		BigDecimal precoUnitario = this.getProduto().getPreco();
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
