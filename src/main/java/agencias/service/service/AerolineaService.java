package agencias.service.service;


import agencias.service.models.dto.Request.AerolineaRequestDTO;
import agencias.service.models.dto.Response.AerolineaResponseDTO;
import agencias.service.models.dto.Response.ResponseDeleteDto;

import java.util.List;

public interface AerolineaService {
    AerolineaResponseDTO guardarAerolinea(AerolineaRequestDTO aerolinea);

    AerolineaResponseDTO editarAerolinea(Long id, AerolineaRequestDTO aerolineaRequest);

    ResponseDeleteDto borrarAerolinea(Long idAerolinea);

    AerolineaResponseDTO traerAerolineaPorId(Long idAerolinea);

    List<AerolineaRequestDTO> listarAerolinea();

}


