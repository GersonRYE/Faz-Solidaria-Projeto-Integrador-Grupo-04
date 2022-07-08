package com.ProjetoIntegrador.FazSolidaria.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoIntegrador.FazSolidaria.exception.CategoriaNaoEncontradoException;
import com.ProjetoIntegrador.FazSolidaria.model.Categoria;
import com.ProjetoIntegrador.FazSolidaria.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public List<Categoria> mostrarCategoriasCadastradas() {
		return categoriaRepository.findAll();
	}

	public Categoria buscarIdCategoriaOuFalhe(Long idCategoria) {
		return categoriaRepository.findById(idCategoria)
				.orElseThrow(() -> new CategoriaNaoEncontradoException(idCategoria));
	}

	public Categoria buscarPeloNomeOuFalhe(String nomeCategoria) {
		return categoriaRepository.findAllByTipoAlimentoIgnoreCase(nomeCategoria)
				.orElseThrow(() -> new CategoriaNaoEncontradoException(
						String.format("Não foi encontrado o nome '%s' na loja FazSolidaria", nomeCategoria)));
	}

	public Categoria cadastrarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria atualizarCadastroCategoria(Categoria categoriaNovo) {
		Categoria categoriaAtual = buscarIdCategoriaOuFalhe(categoriaNovo.getId());
		repassaNovaInfoCategoria(categoriaNovo, categoriaAtual);
		return categoriaRepository.save(categoriaNovo);
	}

	public void deletarCategoria(Long idCategoria) {
		buscarIdCategoriaOuFalhe(idCategoria);
		categoriaRepository.deleteById(idCategoria);
	}

	public void repassaNovaInfoCategoria(Categoria origem, Categoria destino) {
		BeanUtils.copyProperties(origem, destino);
	}
}