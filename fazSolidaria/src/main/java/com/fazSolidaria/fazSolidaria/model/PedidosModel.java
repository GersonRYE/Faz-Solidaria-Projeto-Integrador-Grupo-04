package com.fazSolidaria.fazSolidaria.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_pedido") //tabela HISTORICO PEDIDO
public class PedidosModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datePedido = new java.sql.Date(System.currentTimeMillis());

	@NotBlank(message = "Total do Pedido é obrigatório")
	private double totalPedido;
	
	@ManyToOne
	private ProdutosModel produtos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatePedido() {
		return datePedido;
	}

	public void setDatePedido(Date datePedido) {
		this.datePedido = datePedido;
	}

	public double getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(double totalPedido) {
		this.totalPedido = totalPedido;
	}

}
