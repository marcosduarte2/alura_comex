package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CategoriaDto {
    private Long id;
    private String nome;
    private String status ;

    public CategoriaDto(){}

    public CategoriaDto(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.status = categoria.getStatus().name();
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
