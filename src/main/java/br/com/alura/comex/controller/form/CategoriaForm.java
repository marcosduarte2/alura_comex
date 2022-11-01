package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CategoriaForm {
    @NotNull
    @Length(min = 2)
    private String nome;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getIntanciaCategoria(){
        Categoria cat = new Categoria();
        cat.setNome(this.nome);
        return cat;
    }
}
