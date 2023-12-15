package agencias.service.utils;

import agencias.service.models.dto.Request.UsuarioRequestDTO;
import agencias.service.models.dto.Response.UsuarioResponseDTO;
import agencias.service.models.entity.Usuario;

import java.time.LocalDate;

public class UsuarioUtils {

    public static Usuario usuario1(){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setDni(31545217L);
        usuario.setEmail("jPerez@Gmail.com");
        usuario.setFechaNacimiento(LocalDate.of(1984, 8, 4));
        usuario.setTelefono(1150478963L);

        return usuario;
    }

    public static Usuario usuario2(){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setDni(31545217L);
        usuario.setEmail("jPerez@Gmail.com");
        usuario.setFechaNacimiento(LocalDate.of(1984, 8, 4));
        usuario.setTelefono(1150478963L);
        usuario.setPassword("123");
        usuario.setUsername("test");
        return usuario;
    }

    public static UsuarioRequestDTO usuarioDto1(){
        UsuarioRequestDTO usuario = new UsuarioRequestDTO();
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setDni(31545217L);
        usuario.setEmail("jPerez@Gmail.com");
        usuario.setFechaNacimiento(LocalDate.of(1984, 8, 4));
        usuario.setTelefono(1150478963L);
        usuario.setPassword("123");
        usuario.setUsername("test");
        return usuario;
    }
}
