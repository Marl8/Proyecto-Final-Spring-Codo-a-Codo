package agencias.service.models.dto.Response;

import agencias.service.models.entity.Pasajero;
import agencias.service.models.enums.Clase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketByUserResponseDTO {

    private int numAsiento;
    private double precio;
    private Clase clase;
    private Pasajero pasajero;
}
