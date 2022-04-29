package com.fazSolidaria.fazSolidaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazSolidaria.fazSolidaria.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	public List<Endereco> findAllByUfContainingIgnoreCase(String uf);

	public List<Endereco> findAllByPaisContainingIgnoreCase (String pais);
}
