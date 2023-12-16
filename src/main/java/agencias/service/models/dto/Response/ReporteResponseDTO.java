package agencias.service.models.dto.Response;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteResponseDTO {


    private int numeroReservasVendidas;
    private Double ingresosGenerados;
    private Map<String,Integer> destinosPopulares;


}
