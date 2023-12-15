package agencias.service.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedList;

@Entity
@Table(name = "vuelo")
@NoArgsConstructor
@Data
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    private Long idVuelo;

    @Positive(message = "Debe ser un número positivo")
    @Column(name = "num_vuelo", unique = true)
    private int numVuelo;

    @Min(0)
    @Column(name = "cant_pasajeros")
    private int cantAsientos;

    @Column(name = "disponibilidad")
    private boolean disponibilidad;

    @NotNull(message = "El vuelo debe de tener una fecha")
    @Column(name = "fecha")
    private LocalDate fecha;

    @Embedded
    private Itinerario itinerario;

    @NotNull(message = "Debe tener una aerolínea asociada")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_aerolinea", referencedColumnName = "id_aerolinea")
    private Aerolinea aerolinea;
}
