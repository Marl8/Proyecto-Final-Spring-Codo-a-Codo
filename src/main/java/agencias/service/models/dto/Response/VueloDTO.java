package agencias.service.models.dto.Response;

import agencias.service.models.dto.Request.AerolineaRequestDTO;
import agencias.service.models.entity.Itinerario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VueloDTO {

    private Long idVuelo;
    private int numVuelo;
    private int cantAsientos;
    private boolean disponibilidad;
    private LocalDate fecha;
    private Itinerario Itinerario;
    private AerolineaRequestDTO aerolinea;
}
