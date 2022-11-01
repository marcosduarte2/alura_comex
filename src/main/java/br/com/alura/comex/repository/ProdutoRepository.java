package br.com.alura.comex.repository;


import br.com.alura.comex.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query(value = "select c.nome as categoria,sum(ip.quantidade) as quantidade,sum(ip.preco_unitario * ip.quantidade - (ip.preco_unitario * ip.quantidade)*(ip.desconto/100)  ) as total \n" +
            "from itens_pedido ip\n" +
            "left join produtos p on (ip.produto_id = p.id)\n" +
            "left join categorias c on (p.categoria_id = c.id)\n" +
            "GROUP by c.id", nativeQuery = true)
    public List<ItemRelarioVendaPorCategoria> relatorioVenda();
}
