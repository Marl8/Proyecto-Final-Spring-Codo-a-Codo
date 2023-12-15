package agencias.service.models.dto.Request;

import agencias.service.models.dto.Response.TicketByUserResponseDTO;
import agencias.service.models.entity.Aerolinea;
import agencias.service.models.entity.Itinerario;
import agencias.service.models.enums.TipoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservasByUserRequestDTO {

    private LocalDate fechaReserva;
    private String nombre;
    private String apellido;
    private Long dni;
    private Long telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private TipoPago tipoPago;
    private int numVuelo;
    private LocalDate fecha;
    private Itinerario itinerario;
    private Aerolinea aerolinea;
    private List<TicketByUserResponseDTO> tickets;
}
