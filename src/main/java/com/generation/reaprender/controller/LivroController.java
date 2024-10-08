package com.generation.reaprender.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.reaprender.model.Livro;
import com.generation.reaprender.repository.CategoriaRepository;
import com.generation.reaprender.repository.LivroRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LivroController {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Livro>> getAll() {
		return ResponseEntity.ok(livroRepository.findAll());
	}
	
	@GetMapping("/pagina")
    public ResponseEntity<Page<Livro>> getAllPage(Pageable pageable) {
        return ResponseEntity.ok(livroRepository.findAll(pageable));
    
	}
	
	@GetMapping("/pagina/didatico")
    public ResponseEntity<Page<Livro>> getAllDidaticosPage(Pageable pageable) {
        return ResponseEntity.ok(livroRepository.findByCategoriaDidaticoTrue(pageable));
    
	}
	
	@GetMapping("/pagina/naodidatico")
    public ResponseEntity<Page<Livro>> getAllNaoDidaticosPage(Pageable pageable) {
        return ResponseEntity.ok(livroRepository.findByCategoriaDidaticoFalse(pageable));
    
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> getById(@PathVariable Long id) {
		return livroRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Livro>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(livroRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/autor/{autor}")
	public ResponseEntity<List<Livro>> getByAutor(@PathVariable String autor) {
		return ResponseEntity.ok(livroRepository.findAllByAutorContainingIgnoreCase(autor));
	}
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<List<Livro>> getByCategoria(@PathVariable Long id) {
		return categoriaRepository.findById(id)
	    .map(categoria -> {
	        return ResponseEntity.ok(livroRepository.findAllByCategoria(categoria));
	    })
	    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "A categoria não existe!", null));
	}
	
	@PostMapping
	public ResponseEntity<Livro> post(@Valid @RequestBody Livro livro) {
		if (categoriaRepository.existsById(livro.getCategoria().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livro));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A categoria não existe!", null);
	}
	
	@PutMapping
	public ResponseEntity<Livro> put(@Valid @RequestBody Livro livro) {
		if (livroRepository.existsById(livro.getId())) {
			if (categoriaRepository.existsById(livro.getCategoria().getId())) {
				return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livro));
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A categoria não existe!", null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Livro> livro = livroRepository.findById(id);
		if (livro.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}	
		livroRepository.deleteById(id);
	}
	
}
