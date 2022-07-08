package com.ProjetoIntegrador.FazSolidaria.model;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido_novo")
public class PedidoNovo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String orderTrackingNumber;
	private int quantidadeTotal;
	private BigDecimal precoTotal;
	
	
	@CreationTimestamp
    private Date dataCriacao;

    @UpdateTimestamp
    private Date ultimaAtualizacao;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties(value = "pedidos", allowSetters = true)
    private UsuarioNovo usuario;
    
    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "pedido")
    @JsonIgnoreProperties("pedido")
    private List<ItemPedidoNovo> itensPedido = new ArrayList<>();
    
    public void add(ItemPedidoNovo item){
        if(item != null){
            if(itensPedido == null){
            	itensPedido = new ArrayList<>();
            }
            itensPedido.add(item);
            item.setPedido(this);
        }
    }
	
}
