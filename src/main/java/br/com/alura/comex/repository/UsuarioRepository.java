package br.com.alura.comex.repository;


import java.util.Optional;

import br.com.alura.comex.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
