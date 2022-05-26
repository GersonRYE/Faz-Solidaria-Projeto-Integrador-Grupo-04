package com.ProjetoIntegrador.FazSolidaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoIntegrador.FazSolidaria.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public Optional<Produto> findAllByNomeIgnoreCase(String nome);
	
	public Optional<Produto> findAllById(Long id);

}
