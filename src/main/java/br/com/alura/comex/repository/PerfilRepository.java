package br.com.alura.comex.repository;


import br.com.alura.comex.model.Perfil;
import br.com.alura.comex.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PerfilRepository extends JpaRepository<Perfil, Long> {


}
