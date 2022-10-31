package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoForm {
    @NotNull
    @Length(min = 2)
    private String nome;
    private String descricao;
    @NotNull
    @Min(value = 0)
    private Double preco;
    @NotNull
    private Integer quantidade;
    @NotNull
    private Long idCategoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Produto getInstanciaProduto(CategoriaRepository categoriaRepository){
        Produto produto = new Produto();
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPrecoUnitario(new BigDecimal(this.preco));
        produto.setQuantidadeEstoque(this.quantidade);
        produto.setCategoria(categoriaRepository.getReferenceById(this.idCategoria));
        return produto;
    }
}
