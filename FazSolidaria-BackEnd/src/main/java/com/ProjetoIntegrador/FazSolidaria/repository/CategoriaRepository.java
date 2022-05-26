package com.ProjetoIntegrador.FazSolidaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoIntegrador.FazSolidaria.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public Optional<Categoria> findAllByTipoAlimentoIgnoreCase(String descricao);

}
