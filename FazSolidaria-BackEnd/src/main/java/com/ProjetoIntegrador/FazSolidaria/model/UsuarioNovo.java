package com.ProjetoIntegrador.FazSolidaria.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table()
public class UsuarioNovo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String usuario;
	
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "usuario", allowSetters = true)
	private List<PedidoNovo> pedidos = new ArrayList<>();
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "usuario", allowSetters = true)
    private List<Endereco> enderecos = new ArrayList<>();
    
    public void add(PedidoNovo pedido){
        if(pedido != null){
            if(pedidos == null){
            	pedidos = new ArrayList<>();
            }
            pedidos.add(pedido);
            pedido.setUsuario(this);
        }
    }
    
    public void addEndereco(Endereco endereco){
        if(endereco != null){
            if(enderecos == null){
            	enderecos = new ArrayList<>();
            }
            enderecos.add(endereco);
            endereco.setUsuario(this);
        }
    }
}
