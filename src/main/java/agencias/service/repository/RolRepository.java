package agencias.service.repository;

import agencias.service.models.entity.Rol;
import agencias.service.models.enums.ERol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findRolByName(ERol name);
}
