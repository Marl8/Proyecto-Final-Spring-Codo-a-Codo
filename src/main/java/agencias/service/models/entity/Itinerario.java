package agencias.service.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Itinerario {

    @Column(name = "ciudad_origen")
    @NotBlank(message = "Debe incluir una ciudad")
    private String ciudadOrigen;

    @Column(name = "pais_origen")
    @NotBlank(message = "Debe incluir un pais")
    private String paisOrigen;

    @Column(name = "ciudad_destino")
    @NotBlank(message = "Debe incluir una ciudad")
    private String ciudadDestino;

    @Column(name = "pais_destino")
    @NotBlank(message = "Debe incluir un pais")
    private String paisDestino;

    @Column(name = "hora_salida")
    @NotBlank(message = "Debe un horario de salida")
    private String horaSalida;

    @Column(name = "hora_llegada")
    @NotBlank(message = "Debe un horario de llegada")
    private String horaLLegada;
}
