package com.ProjetoIntegrador.FazSolidaria.dto;

import java.util.List;

import com.ProjetoIntegrador.FazSolidaria.model.Endereco;
import com.ProjetoIntegrador.FazSolidaria.model.ItemPedidoNovo;
import com.ProjetoIntegrador.FazSolidaria.model.PedidoNovo;
import com.ProjetoIntegrador.FazSolidaria.model.UsuarioNovo;

import lombok.Data;

@Data
public class Purchase {
	private UsuarioNovo usuario;
	private Endereco endereco;
	private PedidoNovo pedido;
	private List<ItemPedidoNovo> itensPedido;
}
