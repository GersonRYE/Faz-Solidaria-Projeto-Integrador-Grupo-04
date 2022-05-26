package com.ProjetoIntegrador.FazSolidaria.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "itemPedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(nullable = false, name = "pedido_id")
	@ManyToOne
	private Pedido pedido;

	@JoinColumn(nullable = false, name = "produto_id")
	@ManyToOne
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
		this.pedido.setSubtotal(getPrecoTotal());
	}

	public void estoqueProduto() {
		this.produto.setEstoque(produto.getEstoque() - getQuantidade());
	}

	public void retornaEstoque() {
		this.produto.setEstoque(produto.getEstoque() + getQuantidade());
	}
}
