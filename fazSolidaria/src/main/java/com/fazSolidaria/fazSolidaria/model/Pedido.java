package com.fazSolidaria.fazSolidaria.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fazSolidaria.fazSolidaria.exception.StatusPedidoException;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private BigDecimal subtotal;
	private BigDecimal frete;
	private BigDecimal valorTotal;

//	@Temporal(TemporalType.TIMESTAMP)
//	private Date dataConfirmacao = new java.sql.Date(System.currentTimeMillis());

	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConfirmacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCancelamento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrega;

	@ManyToOne
	// @JoinColumn(name = "cliente_id", nullable = false)
	private Usuario cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("pedido")
	private List<ItemPedido> itens = new ArrayList<>();
	
//	public void comecaZero() {
//		if(getSubtotal() == null) {
//			setSubtotal(BigDecimal.ZERO);
//		}
//	}

	public void valorTotalPedido() {
		for (int i = 0; i < getItens().size(); i++) {
//			BigDecimal valorTotalPedido = this.getItens().get(i).getPrecoTotal();
//			setValorTotal(valorTotalPedido.add(getValorTotal()));		
			BigDecimal subTotalPedido = this.getItens().get(i).getPrecoTotal();
			setSubtotal(subTotalPedido.add(getSubtotal()));
		}
//		     setValorTotal(getValorTotal().add(getFrete()));  
		     setValorTotal(getSubtotal().add(getFrete())); 
	}

	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(new java.sql.Date(System.currentTimeMillis()));
	}

	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(new java.sql.Date(System.currentTimeMillis()));
	}

	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(new java.sql.Date(System.currentTimeMillis()));
	}

	private void setStatus(StatusPedido novoStatus) {
		if (getStatus().naoPodeAlterarPara(novoStatus)) {
			throw new StatusPedidoException(String.format("Status do pedido %d não pode ser alterado de %s para %s ", getId(),
					getStatus().getDescricao(), novoStatus.getDescricao()));
		}
		this.status = novoStatus;
	}
}
