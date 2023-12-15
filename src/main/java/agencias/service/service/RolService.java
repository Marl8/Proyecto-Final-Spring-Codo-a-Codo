package agencias.service.service;

import agencias.service.models.dto.Request.RolDTO;
import agencias.service.models.dto.Response.RolResponseDTO;

import java.util.List;

public interface RolService {

    RolResponseDTO save(RolDTO rolDto);

    RolResponseDTO asignarRol(Long idRol, Long idUsuario);

    List<RolDTO> findAll();
}
