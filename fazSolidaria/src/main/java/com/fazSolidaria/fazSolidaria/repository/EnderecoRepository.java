package com.fazSolidaria.fazSolidaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazSolidaria.fazSolidaria.model.EnderecoModel;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

	public List<EnderecoModel> findAllByUfContainingIgnoreCase(String uf);

	public List<EnderecoModel> findAllByPaisContainingIgnoreCase (String pais);
}
