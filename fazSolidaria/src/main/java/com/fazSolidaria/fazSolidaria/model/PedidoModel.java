package com.fazSolidaria.fazSolidaria.model;

import java.math.BigDecimal;
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
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_pedido")
public class PedidoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private BigDecimal subTotal;

	private BigDecimal valorTotal;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePedido = new java.sql.Date(System.currentTimeMillis());

	@ManyToOne
	private UsuarioModel cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedidoModel> itens;

	@NotBlank(message = "A quantidade é obrigatório")
	private int quantidade;

	public void calcularValorTotal() {
		getItens().forEach(ItemPedidoModel::calcularPrecoTotal);
		this.subTotal = getItens().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		// this.valorTotal = this.subTotal.add(this.taxaFrete);
	}

}
