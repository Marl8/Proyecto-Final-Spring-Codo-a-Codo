package agencias.service.models.dto.Response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private String nombre;
    private String apellido;
    private String email;

}
