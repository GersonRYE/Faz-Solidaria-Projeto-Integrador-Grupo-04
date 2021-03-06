package com.fazSolidaria.fazSolidaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazSolidaria.fazSolidaria.model.PedidosModel;

@Repository
public interface PedidoRepository extends JpaRepository<PedidosModel, Long> {

}
