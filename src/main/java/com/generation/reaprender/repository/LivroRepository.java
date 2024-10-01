package com.generation.reaprender.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.reaprender.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

	public List<Livro> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
	
	public List<Livro> findAllByAutorContainingIgnoreCase(@Param("autor") String autor);
	
    Page<Livro> findByCategoriaDidaticoFalse(Pageable pageable);
    
    Page<Livro> findByCategoriaDidaticoTrue(Pageable pageable);
    
    Page<Livro> findAll(Pageable pageable);


}
