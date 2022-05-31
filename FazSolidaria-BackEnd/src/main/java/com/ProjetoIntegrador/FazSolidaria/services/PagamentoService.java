package com.ProjetoIntegrador.FazSolidaria.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
	
	@Service
	public class PagamentoService {

	    private final RestTemplate restTemplate;

	    public PagamentoService(RestTemplateBuilder restTemplateBuilder) {
	        this.restTemplate = restTemplateBuilder.build();
	    }

	    public String getPostsPlainJSON() {
	        String url = "https://jsonplaceholder.typicode.com/posts";
	        return this.restTemplate.getForObject(url, String.class);
	    }
	}

