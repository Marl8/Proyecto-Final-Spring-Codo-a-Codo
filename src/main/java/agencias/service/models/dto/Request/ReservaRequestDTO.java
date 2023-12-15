package agencias.service.models.dto.Request;

import agencias.service.models.enums.TipoPago;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservaRequestDTO {


    @NotNull(message="Debe tener una fecha")
    @FutureOrPresent(message="La fecha debe ser la actual o una fecha futura")
    private LocalDate fechaReserva;

    @NotNull(message ="Debe tener un tipo de pago asociado")
    private TipoPago tipoPago;

    private Long idVuelo;

    private Long idUsuario;

    private List<TicketRequestDTO> tickets;
}
