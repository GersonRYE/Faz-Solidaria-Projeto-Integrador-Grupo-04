package com.fazSolidaria.fazSolidaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazSolidaria.fazSolidaria.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	public Endereco findByUfIgnoreCase(String uf);
	
	public Endereco findByLocalidadeIgnoreCase (String localidade);

	public Endereco findByPaisIgnoreCase (String pais);
}
