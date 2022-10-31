package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.dto.ProdutoDto;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.controller.form.ProdutoForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(ProdutoController.PATH)
public class ProdutoController {

    public final static String PATH = "/api/produtos";

    private ProdutoRepository produtoRepository;

    private CategoriaRepository categoriaRepository;
    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository){
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody(required = true) @Valid ProdutoForm produtoForm, UriComponentsBuilder uriBuilder){
        Produto produto = this.produtoRepository.save(produtoForm.getInstanciaProduto(this.categoriaRepository));
        URI uri = uriBuilder.path(PATH).buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(produto));
    }

    @GetMapping
    public Page<ProdutoDto> listar(@RequestParam  int pagina, @RequestParam int quantidade){
        Pageable pag = PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.ASC, "nome"));
        return ProdutoDto.converter(this.produtoRepository.findAll(pag));
    }

}
