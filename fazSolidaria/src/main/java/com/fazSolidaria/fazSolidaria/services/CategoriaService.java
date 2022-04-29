package com.fazSolidaria.fazSolidaria.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazSolidaria.fazSolidaria.model.Categoria;
import com.fazSolidaria.fazSolidaria.repository.CategoriaRepository;

@Service
public class CategoriaService{

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> mostrarTodasCategorias () {
		return categoriaRepository.findAll();
	}
	
	public Categoria buscarPeloNome (String nome) {
		return categoriaRepository.findAllByTipoAlimentoIgnoreCase(nome);
	}
	
	public Categoria codigoCategoria(Long id) {
		return categoriaRepository.findById(id).orElseThrow(()-> new RuntimeException("Não Existe! - NOT FOUND"));
	}
	
	public Categoria cadastrarOuAtualizarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void deletarCategoria(Long id) {
		codigoCategoria(id);
		categoriaRepository.deleteById(id);
	}
	
	public void atualizaInformacao(Categoria origem, Categoria destino) {
		BeanUtils.copyProperties(origem, destino);
	}
}