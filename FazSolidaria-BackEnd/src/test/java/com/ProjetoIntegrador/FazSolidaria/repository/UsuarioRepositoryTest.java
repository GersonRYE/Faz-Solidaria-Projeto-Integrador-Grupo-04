package com.ProjetoIntegrador.FazSolidaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ProjetoIntegrador.FazSolidaria.model.Usuario;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsuarioRepositoryTest {
	

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start(){
		usuarioRepository.deleteAll();
		usuarioRepository.save(new Usuario (0L,"753.909.450-85", "Felipe Silva", "felipe_silva@gmail.com","Test@2020", "25/06/1974", "11062722616"));
		usuarioRepository.save(new Usuario (0L,"778.787.700-69","Thiago Oliveira", "thiago_Oliveira@gmail.com","Test@2019","30/01/1999", "12980807070"));
		usuarioRepository.save(new Usuario (0L,"567.305.230-85", "Luis Fernandes", "luis_fernandes@gmail.com","Test@2017","07/08/2000", "85960607070"));
		usuarioRepository.save(new Usuario (0L,"455.939.820-82","Victor Pereira", "victor_pereira@gmail.com","Test@2015","02/12/1967", "31950706050"));
	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("felipe_silva@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("felipe_silva@gmail.com")); 
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Thiago Oliveira");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Thiago Oliveira"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Luis Fernandes"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Victor Pereira"));
	}

}
