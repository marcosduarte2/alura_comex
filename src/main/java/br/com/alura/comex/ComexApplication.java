package br.com.alura.comex;

import br.com.alura.comex.model.Perfil;
import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.PerfilRepository;
import br.com.alura.comex.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
@EnableCaching
public class ComexApplication {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner setupDefaultUser() {
		Optional<Usuario> usuario = this.usuarioRepository.findByEmail("admin");
		if(usuario.isPresent()){
			return ArgsAnnotationPointcut -> {};
		}else {
			Usuario u = new Usuario();
			Perfil p = new Perfil("ADMIN");
			u.setEmail("admin");
			u.setSenha(this.passwordEncoder.encode("admin"));
			u.setNome("Administrador");
			u.getPerfis().add(p);
			return args -> {
				this.perfilRepository.save(p);
				this.usuarioRepository.save(u);
			};

		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ComexApplication.class, args);
	}

}
