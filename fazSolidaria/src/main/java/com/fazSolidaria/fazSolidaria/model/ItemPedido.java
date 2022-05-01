package com.fazSolidaria.fazSolidaria.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	//@JoinColumn(nullable = false, name = "pedido_id")
	@ManyToOne // Um item pedido para muitos pedidos
	private Pedido pedido;

	//@JoinColumn(nullable = false, name = "produto_id")
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

	public void valorTotalPedido() {
		this.pedido.setValorTotal(getPrecoTotal());
		}
	
//	public void valorTotalPedido() {
//	for (int i = 1; i < getItens().size(); i++) {
//		BigDecimal valorTotalPedido = this.getItens().get(i).getPrecoTotal();
//		this.setValorTotal(valorTotalPedido.add(getValorTotal()));
//	}
//}
}
