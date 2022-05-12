package com.fazSolidaria.fazSolidaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fazSolidaria.fazSolidaria.model.UsuarioModel;
import com.fazSolidaria.fazSolidaria.repository.UsuarioRepository;
import com.fazSolidaria.fazSolidaria.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UsuarioControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@BeforeAll
	void start(){
		usuarioRepository.deleteAll();
	}
	
	@Test
	@Order(1)
	@DisplayName("Cadastrar um usuário:")
	public void CriarUsuario() {
		HttpEntity <UsuarioModel> requisicao = new HttpEntity <UsuarioModel> (new UsuarioModel (0L,"52192772009","Lucas","lucas@email.com", "12345","18/08/1990","920102010","foto1.jpg"));
		ResponseEntity<UsuarioModel> resposta = testRestTemplate.exchange("/usuario/cadastrar", HttpMethod.POST, requisicao, UsuarioModel.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
		assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
		assertEquals(requisicao.getBody().getFoto(), resposta.getBody().getFoto());
		
		//long id, String cpf, String nome, String usuario, String senha, String dataNasc, String telefone, String foto
	}
	
	
	
	@Test
	@Order(2)
	@DisplayName("Não é permitido acesso duplicado")
	public void AcessoDuplicarUsuario() {
		usuarioService.CadastrarUsuario(new UsuarioModel(0L,"52192772009","Lucas","lucas@email.com", "12345","18/08/1990","920102010","foto1.jpg"));
	HttpEntity<UsuarioModel> requisicao = new HttpEntity<UsuarioModel>(new UsuarioModel(0L,"52192772009","Lucas","lucas@email.com", "12345","18/08/1990","920102010","foto1.jpg"));
	ResponseEntity <UsuarioModel> resposta = testRestTemplate.exchange("/ususario/cadastrar", HttpMethod.POST, requisicao, UsuarioModel.class);
	assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
	}
	
	
	
	@Test
	@Order(3)
	@DisplayName("Alterar um usuário")
	public void AlterarUsuario() {
		
		Optional <UsuarioModel> usuarioCreate = usuarioService.CadastrarUsuario(0L,"52192772009","Lucas Cardoso","lucascardoso@email.com", "12345","18/08/1990","920102010","foto1.jpg");
		UsuarioModel usuarioUpdate = new UsuarioModel (usuarioCreate.get().getId(),"52192772009","Lucas Cardoso","lucascardoso@email.com", "12345","18/08/1990","920102010","foto1.jpg");
		HttpEntity<UsuarioModel> requisicao = new HttpEntity<UsuarioModel>(usuarioUpdate);
		ResponseEntity<UsuarioModel> resposta = testRestTemplate.withBasicAuth("root", "root").exchange("/usuarios/cadastrar",HttpMethod.PUT, requisicao, UsuarioModel.class);
				
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(usuarioUpdate.getNome(), resposta.getBody().getNome());
		assertEquals(usuarioUpdate.getUsuario(), resposta.getBody().getUsuario());
		assertEquals(usuarioUpdate.getFoto(), resposta.getBody().getFoto());
	}
	
	@Test
	@Order(4)
	@DisplayName("Listar todos os usuário")
	public void MostrarTodosUsuarios() {
		usuarioService.CadastrarUsuario(0L,"52192772009","Camila Oliveira","camila@email.com", "323232","18/08/1990","920102010","foto1.jpg");
		usuarioService.CadastrarUsuario(0L,"52192772009","Fernando Silva","fernando@email.com", "8775656","18/08/1990","920102010","foto1.jpg");
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root", "root")
				.exchange("/usuario/all", HttpMethod.GET,null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
}
