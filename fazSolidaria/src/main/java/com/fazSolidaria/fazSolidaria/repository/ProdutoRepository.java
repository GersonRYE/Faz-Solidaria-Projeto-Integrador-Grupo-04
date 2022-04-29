package com.fazSolidaria.fazSolidaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazSolidaria.fazSolidaria.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public Produto findAllByNomeIgnoreCase(String nome);

}
