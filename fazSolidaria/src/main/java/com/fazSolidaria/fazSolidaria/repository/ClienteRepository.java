package com.fazSolidaria.fazSolidaria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazSolidaria.fazSolidaria.model.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

	public Optional<ClienteModel> findByEmail(String email);

	public List<ClienteModel> findAllByNomeClienteContainingIgnoreCase(String nomeCliente);

}
