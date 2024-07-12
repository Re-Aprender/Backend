package com.generation.reaprender.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O Atributo tipolivro é obrigatório!")
	@Size(min = 3, max = 50, message = "O atributo tipo_livro deve ter mínimo 3 caracteres e no máximo 50!")
	private String tipoLivro;
	
	@NotNull(message = "O Atributo genero é obrigatório!")
	@Size(min = 3, max = 50, message = "O atributo genero deve ter mínimo 3 caracteres e no máximo 50!")
	private String genero;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categorias")
	private List<Livro> livro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipolivro() {
		return tipoLivro;
	}

	public void setTipolivro(String tipoLivro) {
		this.tipoLivro = tipoLivro;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	
}
