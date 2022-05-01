package com.fazSolidaria.fazSolidaria.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private BigDecimal valorTotal;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPedido = new java.sql.Date(System.currentTimeMillis());

	@ManyToOne
	// @JoinColumn(name = "cliente_id", nullable = false)
	private Usuario cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("pedido")
	private List<ItemPedido> itens = new ArrayList<>();

	public void valorTotalPedido() {
		for (int i = 0; i < getItens().size(); i++) {
			BigDecimal valorTotalPedido = this.getItens().get(i).getPrecoTotal();
			setValorTotal(valorTotalPedido.add(getValorTotal()));
		}
	}

//	public void calcularValorTotal() {
////		setValorTotal(getItens().forEach(ItemPedido::getPrecoTotal));
//		for(int i = 0 ; i < this.itens.size(); i++) {
//			BigDecimal precoTotal = this.getItens().get(i).getPrecoTotal();
//			setValorTotal(precoTotal.add(this.getValorTotal()));
//		}

//		setValorTotal(getItens().forEach(ItemPedido::calcularPrecoTotal));

//		setSubTotal(getItens().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add));
//		this.subTotal = getItens().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
//		setValorTotal(getItens().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add));
//		this.valorTotal = this.subTotal;
	// setValorTotal(valorTotal);
//	}

}
