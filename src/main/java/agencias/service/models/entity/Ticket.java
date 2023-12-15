package agencias.service.models.entity;

import agencias.service.models.enums.Clase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Long idTicket;

    @Positive(message = "Debe ser un numero positivo")
    @Column(name = "num_asiento")
    private int numAsiento;

    @Positive(message = "Debe ser un numero positivo")
    @Column(name = "precio")
    private double precio;

    @NotNull(message = "Debe incluir una clase")
    @Enumerated(EnumType.STRING)
    @Column(name = "clase")
    private Clase clase;

    @NotNull(message = "Debe incluir un pasajero")
    @Embedded
    private Pasajero pasajero;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_reserva", referencedColumnName = "id_reserva")
    private Reserva reserva;

    /*@ManyToOne
    @JoinColumn(name = "fk_vuelo", referencedColumnName = "id_vuelo")
    private Vuelo vuelo;*/

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", numAsiento=" + numAsiento +
                ", precio=" + precio +
                ", clase=" + clase +
                ", pasajero=" + pasajero +
                '}';
    }
}
