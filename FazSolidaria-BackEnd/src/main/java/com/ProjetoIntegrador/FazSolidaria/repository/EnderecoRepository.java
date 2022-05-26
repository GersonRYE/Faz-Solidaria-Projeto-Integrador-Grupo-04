package com.ProjetoIntegrador.FazSolidaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoIntegrador.FazSolidaria.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	public Optional<Endereco> findByUfIgnoreCase(String uf);

	public Optional<Endereco> findByLocalidadeIgnoreCase(String localidade);

}
