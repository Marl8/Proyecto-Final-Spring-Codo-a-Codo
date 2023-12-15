package agencias.service.models.dto.Response;

import agencias.service.models.dto.Request.AerolineaRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AerolineaResponseDTO {

    private String razonSocial;

    private String cuit;

    private String message;
}
