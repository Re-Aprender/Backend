package com.generation.reaprender.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GenerationType;


@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O Atributo tipo_livro é obrigatório!")
	@Size(min = 3, max = 255, message = "O atributo tipo_livro deve ter mínimo 3 caracteres e no máximo 255!")
	private String tipo_livro;
	
	@NotNull(message = "O Atributo genero é obrigatório!")
	@Size(min = 3, max = 255, message = "O atributo genero deve ter mínimo 3 caracteres e no máximo 255!")
	private String genero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo_livro() {
		return tipo_livro;
	}

	public void setTipo_livro(String tipo_livro) {
		this.tipo_livro = tipo_livro;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	
}
