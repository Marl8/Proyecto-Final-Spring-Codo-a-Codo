package agencias.service.models.dto.Request;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AerolineaRequestDTO {

    @NotEmpty(message = "Debe tener una Razón social")
    @Size(min = 1, max = 30, message = "Razón social debe tener entre 1 y 30 caracteres")
    private String razonSocial;

    @NotEmpty(message="Debe tener un CUIT")
    @Size(min=11, max=11, message="El CUIT debe tener 11 caracteres")
    @Pattern(regexp ="^[0-9]+$" , message= "El CUIT solo puede contener caracteres numéricos entre 0 y 9")
    private String cuit;

}
