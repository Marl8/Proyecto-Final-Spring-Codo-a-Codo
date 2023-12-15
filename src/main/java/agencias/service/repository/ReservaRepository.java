package agencias.service.repository;

import agencias.service.models.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    //public List<Reserva> findReservasByReservasBetween(LocalDate date1, LocalDate date2);

    @Query("FROM Reserva r WHERE r.usuario.idUsuario = ?1")
    List<Reserva> findReservaByIdUsuario(Long id);
}
