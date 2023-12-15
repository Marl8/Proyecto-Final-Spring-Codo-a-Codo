package agencias.service.models.dto.Response;

import agencias.service.models.dto.Request.VueloRequestDTO;
import lombok.*;

//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VueloResponseDTO {

    private VueloDTO vueloDto;
    private String mensaje;
}
