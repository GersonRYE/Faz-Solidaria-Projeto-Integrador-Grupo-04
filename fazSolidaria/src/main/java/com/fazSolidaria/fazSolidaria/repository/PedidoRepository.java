package com.fazSolidaria.fazSolidaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazSolidaria.fazSolidaria.model.PedidoModel;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {

}
