package agencias.service.models.dto.Response;

import agencias.service.models.dto.Request.UsuarioRequestDTO;
import agencias.service.models.dto.Request.VueloRequestDTO;
import agencias.service.models.enums.Clase;
import agencias.service.models.enums.TipoPago;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponseDTO {

    private String message;
    private String vueloItinerario;
    private Double total;
    private TipoPago tipoPago;
    private LocalDate fechaReserva;

}
