package com.ProjetoIntegrador.FazSolidaria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoIntegrador.FazSolidaria.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);

	public Optional<Produto> findAllByNomeIgnoreCase(String nome);
	
	public Optional<Produto> findAllById(Long id);

}
