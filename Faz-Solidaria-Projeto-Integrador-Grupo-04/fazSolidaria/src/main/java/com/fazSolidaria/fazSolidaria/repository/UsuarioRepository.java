package com.fazSolidaria.fazSolidaria.repository;



	import java.util.List;
	import java.util.Optional;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;

import com.fazSolidaria.fazSolidaria.model.Usuario;

	

	@Repository
	public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

		public Optional<com.fazSolidaria.fazSolidaria.model.Usuario> findByUsuario(String usuario);
		
		public List <Usuario> findAllByNomeContainingIgnoreCase(String nome);
	
	
	
	
	
}
