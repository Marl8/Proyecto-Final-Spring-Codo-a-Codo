package agencias.service;

import agencias.service.models.entity.Rol;
import agencias.service.models.entity.Usuario;
import agencias.service.models.enums.ERol;
import agencias.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

/*
	// Generar un usuario inicial para poder hacer login
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UsuarioRepository repository;

	@Bean
	CommandLineRunner init(){ // Comando que se ejecuta al iniciar el programa
		return args -> {
			Usuario user = Usuario.builder()
					.nombre("Victor")
					.apellido("Sosa")
					.telefono(113568904L)
					.dni(33158459L)
					.email("vicsosa@gmail.com")
					.fechaNacimiento(LocalDate.of(1986, 5,26))
					.username("userPrueba")
					.password(passwordEncoder.encode("12345"))
					.roles(Set.of(Rol.builder()
							.name(ERol.valueOf(ERol.ADMIN.name()))
							.build()))
					.build();
			repository.save(user);
		};
	}
	*/
}
