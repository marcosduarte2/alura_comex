package br.com.alura.comex.service;

import br.com.alura.comex.controller.dto.LinhaVendaProdutoPorCategoriaDto;
import br.com.alura.comex.repository.ItemRelarioVendaPorCategoria;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }


    public List<ItemRelarioVendaPorCategoria> relatorioVendaCategoria(){
        return  this.produtoRepository.relatorioVenda();
    }


}
