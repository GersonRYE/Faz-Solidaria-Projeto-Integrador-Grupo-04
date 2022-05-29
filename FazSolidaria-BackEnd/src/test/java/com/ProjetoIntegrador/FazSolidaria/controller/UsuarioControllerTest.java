package com.ProjetoIntegrador.FazSolidaria.controller;

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

import com.ProjetoIntegrador.FazSolidaria.model.Usuario;
import com.ProjetoIntegrador.FazSolidaria.repository.UsuarioRepository;
import com.ProjetoIntegrador.FazSolidaria.services.UsuarioService;

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
	void start() {
		usuarioRepository.deleteAll();
	}


	@Test
	@Order(1)
	@DisplayName("Cadastrar um Usuário")
	public void deveCriarUmUsuario()
	{
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, "925.934.790-43","Caio Abreu", "caio.abreu@gmail.com","Teste@1234","20/05/1985", "21940446050", "http://fotoCaioAbreu.jpg", "administrativo"));
		ResponseEntity<Usuario> resposta = testRestTemplate
				.exchange("/usuarios/cadastrar-usuario",HttpMethod.POST,requisicao,Usuario.class);
		
		assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
		assertEquals(requisicao.getBody().getNome(),resposta.getBody().getNome());
		assertEquals(requisicao.getBody().getUsuario(),resposta.getBody().getUsuario());
		assertEquals(requisicao.getBody().getFoto(),resposta.getBody().getFoto());		
	}
	
	@Test
	@Order(2)
	@DisplayName("Não deve permitir duplicação de usuário")
	public void naoDeveDuplicarUsuario()
	{
		usuarioService.cadastrarUsuario(new Usuario(0L,
				"304.981.360-19","Karla Sousa", "karla_sousa@gmail.com","Teste@12345","02/12/1989", "13950906050", "http://fotoKarlaSousa.jpg", "administrativo"));
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L,
				"304.981.360-19","Karla Sousa", "karla_sousa@gmail.com","Teste@12345","02/12/1989", "13950906050", "http://fotoKarlaSousa.jpg", "administrativo"));
		ResponseEntity<Usuario> resposta = testRestTemplate
				.exchange("/usuarios/cadastrar-usuario",HttpMethod.POST,requisicao,Usuario.class);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
	}
	
	@Test
	@Order(3)
	@DisplayName("Alterar um usuário")
	public void deveAlterarUmUsuario()
	{
		Optional<Usuario> usuarioCreate = usuarioService.cadastrarUsuario(new Usuario(0L,
				"287.673.750-73","Camila", "camila_assuncao@gmail.com","Teste@6789","02/09/1995", "17955506050", "http://fotoCamilaAssunção.jpg", "administrativo"));
		Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(),
				"287.673.750-73","Camila Assunção", "camila_assuncao@gmail.com","Teste@6789","02/09/1995", "17955506050", "http://fotoCamilaAssunção.jpg", "administrativo");
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);
		ResponseEntity<Usuario> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuarios/atualizar-usuario",HttpMethod.PUT,requisicao,Usuario.class);
		assertEquals(HttpStatus.OK,resposta.getStatusCode());
		assertEquals(usuarioUpdate.getNome(),resposta.getBody().getNome());
		assertEquals(usuarioUpdate.getUsuario(),resposta.getBody().getUsuario());
		assertEquals(usuarioUpdate.getFoto(),resposta.getBody().getFoto());
	}

}
