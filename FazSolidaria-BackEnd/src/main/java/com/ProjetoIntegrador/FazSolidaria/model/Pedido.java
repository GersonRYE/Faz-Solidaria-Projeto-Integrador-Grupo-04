package com.ProjetoIntegrador.FazSolidaria.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ProjetoIntegrador.FazSolidaria.exception.PedidoNaoEncontradoException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String orderTrackingNumber;

	private BigDecimal subtotal;
	private int quantidadeTotal;
	private BigDecimal frete;
	private BigDecimal valorTotal;

	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConfirmacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCancelamento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrega;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Usuario cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("pedido")
	private List<ItemPedido> itensPedido = new ArrayList<>();

	public void valorTotalPedido() {
		for (int i = 0; i < getItensPedido().size(); i++) {
			BigDecimal subTotalPedido = this.getItensPedido().get(i).getPrecoTotal();
			setSubtotal(subTotalPedido.add(getSubtotal()));
		}
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
			throw new PedidoNaoEncontradoException(
					String.format("Status do pedido %d não pode ser alterado de %s para %s ", getId(),
							getStatus().getDescricao(), novoStatus.getDescricao()));
		}
		this.status = novoStatus;
	}
	
	 public void add(ItemPedido item){
	        if(item != null){
	            if(itensPedido == null){
	            	itensPedido = new ArrayList<>();
	            }
	            itensPedido.add(item);
	            item.setPedido(this);
	        }
	    }

}
