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

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        pedido.setOrderTrackingNumber(orderTrackingNumber);
//        pedido.setUsuario(purchase.getUsuario());

        // populate order with orderItems
        List<ItemPedidoNovo> itensPedido = purchase.getItensPedido();
        itensPedido.forEach(item -> pedido.add(item));

        // populate order with shippingAddress and billingAddress
//        order.setBillingAddress(purchase.getBillingAddress());
//        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        UsuarioNovo usuario = purchase.getUsuario();
        usuario.add(pedido);
        
        Endereco endereco = purchase.getEndereco();
        usuario.addEndereco(endereco);

//         save to the database
        usuarioRepository.save(usuario);
        
        // salvar endereco
        enderecoRespository.save(endereco);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
	}
	
	 private String generateOrderTrackingNumber() {

	        // generate a random UUID (UUID version-4)
	        return UUID.randomUUID().toString();
	    }

}
