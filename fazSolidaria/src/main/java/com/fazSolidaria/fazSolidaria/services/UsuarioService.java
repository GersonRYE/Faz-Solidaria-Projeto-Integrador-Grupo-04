package com.fazSolidaria.fazSolidaria.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazSolidaria.fazSolidaria.model.UsuarioModel;
import com.fazSolidaria.fazSolidaria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public List<UsuarioModel> mostrarTodosUsuarios() {
		return usuarioRepository.findAll();
	}

	public UsuarioModel buscarNome(String nome) {
		return usuarioRepository.findAllByNomeIgnoreCase(nome);
	}

	public UsuarioModel cadastrarUsuario(UsuarioModel usuario) {
		return usuarioRepository.save(usuario);
	}

	public UsuarioModel atualizarCadastro(UsuarioModel usuario) {
		return usuarioRepository.save(usuario);
	}

	public UsuarioModel codigoUsuario(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Não Existe"));
	}

	// origem-destino do objeto
	public void copiaInfoNovas(UsuarioModel proOrigem, UsuarioModel proDestino) {
		BeanUtils.copyProperties(proOrigem, proDestino);
	}

}
