package com.fazSolidaria.fazSolidaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazSolidaria.fazSolidaria.model.CarrinhoModel;

@Repository
public interface DetalhesPedidoRepository extends JpaRepository<CarrinhoModel, Long> {

}
