package com.ProjetoIntegrador.FazSolidaria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoIntegrador.FazSolidaria.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByUsuario(String usuario);

	public Optional<Usuario> findAllByNomeIgnoreCase(String nome);
	
	public Optional<Usuario>findAllByCpf(String cpf);

	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);

}
