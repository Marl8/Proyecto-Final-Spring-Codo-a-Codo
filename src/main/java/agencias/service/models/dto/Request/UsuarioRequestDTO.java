package agencias.service.models.dto.Request;


import jakarta.validation.constraints.*;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {
    @NotNull(message="Debe tener un nombre")
    private String nombre;

    @NotNull(message="Debe tener un apellido")
    private String apellido;

    @NotNull(message="Debe tener un DNI")
    @Pattern(regexp ="^[0-9]+$" , message= "El DNI solo puede contener caracteres numericos entre 0 y 9")
    @Size(max=8, message="El DNI debe tener 8 digitos como máximo")
    private Long dni;

    @NotNull(message="Debe tener un número de teléfono")
    private Long telefono;

    @NotNull(message="Debe tener un email")
    private String email;

    @PastOrPresent(message="Debe ser una fecha válida")
    private LocalDate fechaNacimiento;

    @NotBlank(message="Username es requerido")
    private String username;

    @NotNull(message = "Password es requerido")
    private String password;
}
