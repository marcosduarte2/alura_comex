package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.dto.LinhaVendaProdutoPorCategoriaDto;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ItemRelarioVendaPorCategoria;
import br.com.alura.comex.repository.ProdutoRepository;
import br.com.alura.comex.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CategoriaController.PATH)
public class CategoriaController {

    public final static String PATH = "/api/categorias";

    private final int TAMANHO_ESPERADO = 1;

    private CategoriaRepository categoriaRepository;

    private ProdutoService produtoService;

    public CategoriaController(CategoriaRepository categoriaRepository, ProdutoService produtoService){
        this.categoriaRepository = categoriaRepository;
        this.produtoService = produtoService;
    }


    @PostMapping
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody(required = true) @Valid CategoriaForm categoriaForm, UriComponentsBuilder uriBuilder){
        Categoria categoria = this.categoriaRepository.save(categoriaForm.getIntanciaCategoria());
        URI uri = uriBuilder.path(PATH).buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }


    @GetMapping(value = "/{id}" , produces = {"application/json"})
    public ResponseEntity<CategoriaDto> detalhar(@PathVariable("id") Long id){
        Optional<Categoria> optional = this.categoriaRepository.findById(id);
        if(optional.isPresent()){
            return  ResponseEntity.ok(new CategoriaDto(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/pedidos" , produces = {"application/json"})
    @Operation(summary = "Relatório de vendas", description = "Gera o relatório de vendas agrupado por categoria")
    @Cacheable(value = "relatorioVendas")
    public List<ItemRelarioVendaPorCategoria> relatorioVendaCategoria(){
        return this.produtoService.relatorioVendaCategoria();
    }

}
