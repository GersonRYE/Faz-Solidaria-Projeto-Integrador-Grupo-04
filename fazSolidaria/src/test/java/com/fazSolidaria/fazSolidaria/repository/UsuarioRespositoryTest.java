package com.fazSolidaria.fazSolidaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fazSolidaria.fazSolidaria.model.UsuarioModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsuarioRespositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	void start() {
		usuarioRepository.deleteAll();
		usuarioRepository.save(new UsuarioModel (0L,"52192772009","Lucas Cardoso","lucascardoso@email.com", "12345","18/08/1999","920102010","foto1.jpg"));
		usuarioRepository.save(new UsuarioModel(0L,"39808901042","Camila Oliveira","camila@email.com", "323232","19/01/1989","920102010","foto1.jpg"));
		usuarioRepository.save(new UsuarioModel(0L,"14723104038","Fernando Silva","fernando@email.com", "8775656","07/02/1990","920102010","foto1.jpg"));
		usuarioRepository.save(new UsuarioModel(0L,"13370713071","Jose Felipe","jfelipe@email.com", "5435345","21/12/1993","920102010","foto1.jpg"));
	}
	
	@Test
	@DisplayName("Retornar 1 usuario")
	public void RetornarUsuario() {
		Optional<UsuarioModel> usuario = usuarioRepository.findByUsuario("lucascardoso@email");
		assertTrue(usuario.get().equals("lucascardoso@email.com"));
	}
	
	@Test
	@DisplayName("Retornar 3 usuarios")
	public void RetornarTresUsuarios() {
		List<UsuarioModel> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Jose");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Jose"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Fernando"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Camila"));
	}

}
