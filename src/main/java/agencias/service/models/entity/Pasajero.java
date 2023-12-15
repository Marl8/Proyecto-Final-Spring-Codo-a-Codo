package agencias.service.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Pasajero {

    @NotBlank(message = "el nombre del pasajero es requerido")
    @Column(name = "nombre_pasajero")
    private String nombrePasajero;

    @NotBlank(message = "el dni del pasajero es requerido")
    @Column(name = "dni_pasajero")
    private String dni;

    @NotBlank(message = "la edad pasajero es requerido")
    @Column(name = "edad")
    private int edad;
}
