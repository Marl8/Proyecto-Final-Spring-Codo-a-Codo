package agencias.service.service.impl;

import agencias.service.exceptions.CustomException;
import agencias.service.models.dto.Request.UsuarioRequestDTO;
import agencias.service.models.dto.Response.UsuarioResponseDTO;
import agencias.service.models.entity.Mensaje;
import agencias.service.models.entity.Rol;
import agencias.service.models.entity.Usuario;
import agencias.service.models.enums.ERol;
import agencias.service.repository.RolRepository;
import agencias.service.repository.UsuarioRepository;
import agencias.service.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    UsuarioRepository usuarioRepository;

    RolRepository rolRepository;

    PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
                              RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
    }

    @Override
    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO usuarioRequestDTO) throws CustomException {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findByDni(usuarioRequestDTO.getDni());
            if (usuarioOptional.isPresent()){
                throw new CustomException (HttpStatus.OK, " El Usuario con DNI: " + usuarioRequestDTO.getDni() + " ya existe");
            }
            Usuario usuario = new Usuario();
            usuario.setNombre(usuarioRequestDTO.getNombre());
            usuario.setApellido(usuarioRequestDTO.getApellido());
            usuario.setDni(usuarioRequestDTO.getDni());
            usuario.setTelefono(usuarioRequestDTO.getTelefono());
            usuario.setEmail(usuarioRequestDTO.getEmail());
            usuario.setFechaNacimiento(usuarioRequestDTO.getFechaNacimiento());
            usuario.setUsername(usuarioRequestDTO.getUsername());
            usuario.setPassword(passwordEncoder.encode(usuarioRequestDTO.getPassword()));

            Rol rol = rolRepository.findRolByName(ERol.USER).orElseThrow(
                    () -> new RuntimeException("No existe el rol"));
            Set<Rol> roles = new HashSet<>();
            roles.add(rol);
            usuario.setRoles(roles);

            Usuario usuarioPersist = usuarioRepository.save(usuario);
            return mapToUsuarioResponseDto(usuarioPersist);

        } catch (Exception e){
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar usuario" + e.getMessage());
        }
    }

    @Override
    public UsuarioResponseDTO mapToUsuarioResponseDto(Usuario usuario) {
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setNombre(usuario.getNombre());
        usuarioResponseDTO.setApellido(usuario.getApellido());
        usuarioResponseDTO.setEmail(usuario.getEmail());
        return usuarioResponseDTO;
    }
}
