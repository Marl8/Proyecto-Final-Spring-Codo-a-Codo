package agencias.service.servicesTest;

import agencias.service.exceptions.RolGenericException;
import agencias.service.models.dto.Request.RolDTO;
import agencias.service.models.dto.Response.RolResponseDTO;
import agencias.service.models.entity.Rol;
import agencias.service.models.entity.Usuario;
import agencias.service.repository.RolRepository;
import agencias.service.repository.UsuarioRepository;
import agencias.service.service.impl.RolServiceImpl;
import agencias.service.utils.RolUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RolServiceTest {

    @Mock
    RolRepository repository;

    @Mock
    UsuarioRepository userRepository;

    @InjectMocks
    RolServiceImpl service;

    @Test
    @DisplayName(value = "Test OK para crear un rol")
    void crearRolTestOK(){
        RolDTO argumentSut = RolUtils.rolDto();
        List<Rol> list = RolUtils.listaRoles();
        RolResponseDTO expected = new RolResponseDTO("Rol guardado con éxito");

        when(repository.findAll()).thenReturn(list);

        RolResponseDTO actual = service.save(argumentSut);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName(value = "Test OK para asignar un rol")
    void asignarRolTestOK(){
        Rol argumentSut = RolUtils.rol2();
        Long idRol = 1L;
        Long idUsuario = 1L;
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        Set<Rol> lista = new HashSet<>();
        lista.add(RolUtils.rol1());
        usuario.setRoles(lista);
        RolResponseDTO expected = new RolResponseDTO("Rol asignado con éxito");

        when(repository.findById(any())).thenReturn(Optional.of(argumentSut));
        when(userRepository.findById(any())).thenReturn(Optional.of(usuario));

        RolResponseDTO actual = service.asignarRol(idRol, idUsuario);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName(value = "Test lanzar EXCEPTION por no encontrar tickets")
    void asignarRolTestEXCEPTION(){
        Long id1 = 1L;
        Long id2 = 1L;
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        RuntimeException expected = new RolGenericException("No existen roles con este id");
        when(userRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(repository.findById(any())).thenReturn(Optional.empty());

        RolGenericException actual = assertThrows(RolGenericException.class, () -> service.asignarRol(id1, id2));
        assertEquals(actual.getMessage(), expected.getMessage());
    }

    @Test
    @DisplayName(value = "Test lanzar EXCEPTION por no encontrar tickets")
    void asignarRolTestEXCEPTION2(){
        Long id1 = 1L;
        Long id2 = 1L;
        RuntimeException expected = new RuntimeException("No existen usuarios con este id");
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        RuntimeException actual = assertThrows(RuntimeException.class, () -> service.asignarRol(id1, id2));
        assertEquals(actual.getMessage(), expected.getMessage());
    }

    @Test
    @DisplayName(value = "Test OK para find All roles")
    void findAllRolesTestOK(){
        List<Rol> argumentSut = RolUtils.listaRoles();
        List<RolDTO> expected = RolUtils.listDtoRoles();

        when(repository.findAll()).thenReturn(argumentSut);
        List<RolDTO> actual = service.findAll();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    @DisplayName(value = "Test lanzar EXCEPTION por no encontrar roles")
    void findAllRolesTestEXCEPTION(){

        RolGenericException expected = new RolGenericException("No se han encontrado roles");
        List<Rol> roles = new ArrayList<>();
        when(repository.findAll()).thenReturn(roles);

        RolGenericException actual = assertThrows(RolGenericException.class, () -> service.findAll());
        assertEquals(actual.getMessage(), expected.getMessage());
    }
}
