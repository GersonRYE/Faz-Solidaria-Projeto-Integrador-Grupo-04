package com.ProjetoIntegrador.FazSolidaria.services;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoIntegrador.FazSolidaria.dto.Purchase;
import com.ProjetoIntegrador.FazSolidaria.dto.PurchaseResponse;
import com.ProjetoIntegrador.FazSolidaria.model.Endereco;
import com.ProjetoIntegrador.FazSolidaria.model.ItemPedidoNovo;
import com.ProjetoIntegrador.FazSolidaria.model.PedidoNovo;
import com.ProjetoIntegrador.FazSolidaria.model.UsuarioNovo;
import com.ProjetoIntegrador.FazSolidaria.repository.EnderecoNovoRepository;
import com.ProjetoIntegrador.FazSolidaria.repository.UsuarioNovoRepository;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	@Autowired
	private UsuarioNovoRepository usuarioRepository;
	
	@Autowired
	private EnderecoNovoRepository enderecoRespository;
	

    @Autowired
    public CheckoutServiceImpl(UsuarioNovoRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		  // retrieve the order info from dto
        PedidoNovo pedido = purchase.getPedido();

        // gera numero de pedido
        String orderTrackingNumber = generateOrderTrackingNumber();
        pedido.setOrderTrackingNumber(orderTrackingNumber);

        // armazena os itensPedido no pedido
        List<ItemPedidoNovo> itensPedido = purchase.getItensPedido();
        itensPedido.forEach(item -> pedido.add(item));


        //armazena o usuario no pedido
        UsuarioNovo usuario = purchase.getUsuario();
        usuario.add(pedido);
        
        Endereco endereco = purchase.getEndereco();
        usuario.addEndereco(endereco);

        // salva no banco do usuario
        usuarioRepository.save(usuario);
        
        // salvar no banco do endereco
        enderecoRespository.save(endereco);

        // reurn da resposta
        return new PurchaseResponse(orderTrackingNumber);
	}
	
	
	 private String generateOrderTrackingNumber() {
	        // generate a random UUID (UUID version-4)
	        return UUID.randomUUID().toString();
	    }

}
