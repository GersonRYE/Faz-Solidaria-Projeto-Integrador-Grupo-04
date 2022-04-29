package com.fazSolidaria.fazSolidaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazSolidaria.fazSolidaria.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public Categoria findAllByTipoAlimentoIgnoreCase(String descricao);
}
