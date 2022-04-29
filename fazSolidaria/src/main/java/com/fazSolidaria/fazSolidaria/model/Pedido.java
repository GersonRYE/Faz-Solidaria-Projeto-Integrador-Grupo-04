package com.fazSolidaria.fazSolidaria.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private BigDecimal subTotal;

	private BigDecimal valorTotal;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPedido = new java.sql.Date(System.currentTimeMillis());

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Usuario cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens;

////	@NotBlank(message = "A quantidade é obrigatório")
//	private int quantidade;

	public void calcularValorTotal() {
		getItens().forEach(ItemPedido::calcularPrecoTotal);
		this.subTotal = getItens().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		this.valorTotal = this.subTotal;
	}

}
