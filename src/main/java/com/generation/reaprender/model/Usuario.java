package com.generation.reaprender.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "O nome de usuário é obrigatório.")
    @Size(min = 3, max = 50, message = "O atributo nome deve ter no mínimo 3 caracteres e no máximo 50!")
    private String nome;
    
    @NotNull(message = "O Atributo email é obrigatório.")
    @Size(min = 13, max = 255, message = "O atributo email deve ter mínimo 13 caracteres e no máximo 255!")
    private String email;
    
    @NotNull(message = "O Atributo senha é obrigatório.")
    @Size(min = 8, message = "O atributo senha deve ter mínimo 8 caracteres!")
    private String senha;
    
    @Size(min = 13, max = 255, message = "O atributo foto deve ter mínimo 13 caracteres e no máximo 255!")
    private String foto;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}