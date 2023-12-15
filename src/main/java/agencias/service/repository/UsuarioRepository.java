package agencias.service.repository;

import agencias.service.models.dto.Response.UsuarioResponseDTO;
import agencias.service.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByDni(Long dni);

    Optional<Usuario> findByUsername(String username);

}
