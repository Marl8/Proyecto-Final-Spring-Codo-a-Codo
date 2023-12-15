package agencias.service.models.dto.Request;

import agencias.service.models.enums.ERol;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolDTO {

    @NotNull(message = "Name no puede ser Null")
    private ERol name;
}
