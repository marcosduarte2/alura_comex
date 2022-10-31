package br.com.alura.comex.repository;


import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
