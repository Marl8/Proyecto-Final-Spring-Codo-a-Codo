package agencias.service.models.dto.Response;

import agencias.service.models.dto.Request.TicketRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class TicketResponseDTO {

    private TicketRequestDTO ticket;

    private String message;
}
