package com.fazSolidaria.fazSolidaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fazSolidaria.fazSolidaria.services.StatusPedidoService;

@RestController
@RequestMapping("/pedidos/{idPedido}")
public class StatusPedidoController {

	@Autowired
	private StatusPedidoService status;
	
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable Long idPedido) {
		status.confirmar(idPedido);
		System.out.println(status.toString() + " Teste");
	}
	
	@PutMapping("/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable Long idPedido) {
		status.cancelar(idPedido);
		System.out.println(status.toString() + " Teste");
	}
	
	@PutMapping("/entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entrega(@PathVariable Long idPedido) {
		status.entregar(idPedido);
		System.out.println(status.toString() + " Teste");
	}
}
