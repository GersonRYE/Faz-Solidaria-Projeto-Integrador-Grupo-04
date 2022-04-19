package com.fazSolidaria.fazSolidaria.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name = "tb_pedidos")
public class HistoricoPedidosModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedido;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePedido = new java.sql.Date(System.currentTimeMillis());
	
	@NotBlank (message = "Total do Pedido é obrigatório")
	private double totalPedido;
	
	
	public long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
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
