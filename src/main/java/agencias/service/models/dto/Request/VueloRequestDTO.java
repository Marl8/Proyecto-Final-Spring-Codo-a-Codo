package agencias.service.models.dto.Request;

import agencias.service.models.entity.Itinerario;

import jakarta.validation.constraints.*;

import lombok.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VueloRequestDTO {

    @Positive(message="E número de vuelo no puede ser un negativa")
    private int numVuelo;

    @Positive(message="La cantidad de pasajeros no puede ser un negativa")
    @Min(value =1,message="La cantidad de pasajeros debe ser mayor a 0")
    @Max(value =350, message="La cantidad de pasajeros no debe ser mayor a 350")
    private int cantAsientos;

    @AssertTrue(message="El vuelo debe indicar que tiene disponibilidad de asientos")
    private boolean disponibilidad;

    @NotNull(message="Debe tener una fecha")
    @FutureOrPresent(message="La fecha debe ser actual o fecha futura")
    private LocalDate fecha;

    private Itinerario Itinerario;

    private Long idAerolinea;
}
