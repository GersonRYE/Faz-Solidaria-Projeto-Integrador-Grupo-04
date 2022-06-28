package com.ProjetoIntegrador.FazSolidaria.services;

import com.ProjetoIntegrador.FazSolidaria.dto.Purchase;
import com.ProjetoIntegrador.FazSolidaria.dto.PurchaseResponse;

public interface CheckoutService {
	 PurchaseResponse placeOrder(Purchase purchase);
}
