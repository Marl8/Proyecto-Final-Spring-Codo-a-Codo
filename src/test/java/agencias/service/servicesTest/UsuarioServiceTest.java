package agencias.service.servicesTest;

import agencias.service.models.dto.Request.UsuarioRequestDTO;
import agencias.service.models.dto.Response.UsuarioResponseDTO;
import agencias.service.models.entity.Rol;
import agencias.service.models.entity.Usuario;
import agencias.service.repository.RolRepository;
import agencias.service.repository.UsuarioRepository;
import agencias.service.service.impl.UsuarioServiceImpl;
import agencias.service.utils.RolUtils;
import agencias.service.utils.UsuarioUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    UsuarioRepository repository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    RolRepository rolRepository;

    @InjectMocks
    UsuarioServiceImpl service;

    @Test
    @DisplayName("Test OK para crear usuario")
    void crearUsuarioTestOk(){
        UsuarioRequestDTO dto = UsuarioUtils.usuarioDto1();
        Usuario argumentSut = UsuarioUtils.usuario2();
        Rol rol = RolUtils.rol1();
        UsuarioResponseDTO expected = new UsuarioResponseDTO(argumentSut.getNombre(), argumentSut.getApellido(),
                argumentSut.getEmail());

        when(repository.findByDni(any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("$2a$12$rv6hPy.kQVhqwcnpvT5KSeI1r1Y.okGygjGRsCd.OiikVwmJn2qFS");
        when(rolRepository.findRolByName(any())).thenReturn(Optional.of(rol));
        when(repository.save(any())).thenReturn(argumentSut);

        UsuarioResponseDTO actual = service.createUsuario(dto);

        assertEquals(expected, actual);
    }
}
