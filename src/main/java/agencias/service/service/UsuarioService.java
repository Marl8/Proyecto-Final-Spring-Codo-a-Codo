package agencias.service.service;

import agencias.service.exceptions.CustomException;
import agencias.service.models.dto.Request.UsuarioRequestDTO;
import agencias.service.models.dto.Response.UsuarioResponseDTO;
import agencias.service.models.entity.Usuario;

public interface UsuarioService {
    UsuarioResponseDTO createUsuario(UsuarioRequestDTO usuarioRequestDTO) throws CustomException;

    UsuarioResponseDTO mapToUsuarioResponseDto(Usuario usuario);

}
