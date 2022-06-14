//package com.ProjetoIntegrador.FazSolidaria.controller;
//
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//
//import com.ProjetoIntegrador.FazSolidaria.model.Post;
//import com.ProjetoIntegrador.FazSolidaria.services.PagamentoService;
//
//
//public class PagamentoController {
//
//	public Post createPost() {
//	    String url = "http://localhost:3000/pagamentos/pagamento";
//
//	    // create headers
//	    HttpHeaders headers = new HttpHeaders();
//	    // set `content-type` header
//	    headers.setContentType(MediaType.APPLICATION_JSON);
//	    // set `accept` header
//	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//	    // create a map for post parameters
//	    Map<String, Object> map = new HashMap<>();
//	    map.put("value", 15.90);
//	    map.put("user", "oma");
//	    map.put("card", "5472025174919915");
//
//	    // build the request
//	    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
//
//	    // send POST request
//	    ResponseEntity<Post> response = this.restTemplate.postForEntity(url, entity, Post.class);
//
//	    // check response status code
//	    if (response.getStatusCode() == HttpStatus.CREATED) {
//	        return response.getBody();
//	    } else {
//	        return null;
//	    }
//	}
//}
