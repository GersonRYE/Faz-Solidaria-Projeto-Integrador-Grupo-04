package com.fazSolidaria.fazSolidaria.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazSolidaria.fazSolidaria.model.Usuario;
import com.fazSolidaria.fazSolidaria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public List<Usuario> mostrarTodosUsuarios() {
		return usuarioRepository.findAll();
	}

	public Usuario buscarNome(String nome) {
		return usuarioRepository.findAllByNomeIgnoreCase(nome);
	}

	public Usuario cadastrarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Usuario atualizarCadastro(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Usuario codigoUsuario(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Não Existe"));
	}

	// origem-destino do objeto
	public void copiaInfoNovas(Usuario proOrigem, Usuario proDestino) {
		BeanUtils.copyProperties(proOrigem, proDestino);
	}

}
