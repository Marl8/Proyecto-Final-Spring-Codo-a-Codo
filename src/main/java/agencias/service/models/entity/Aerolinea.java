package agencias.service.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="aerolineas")
public class Aerolinea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_aerolinea")
    private Long idAerolinea;

    @Size(min = 5, max = 30, message = "Razón social debe tener entre 5 y 30 caracteres")
    @Column(name = "razon_social")
    private String razonSocial;

    @Size(max = 11, message = "El cuit debe tener 11 caracteres")
    @Column(name = "cuit")
    private String cuit;

}
