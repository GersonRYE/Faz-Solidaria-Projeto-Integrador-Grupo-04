package com.fazSolidaria.fazSolidaria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazSolidaria.fazSolidaria.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

	public List<UsuarioModel> findAllByNomeContainingIgnoreCase(String nome);

	public Optional<UsuarioModel> findByUsuario(String usuario);

}
