package agencias.service.controllerTest;

import agencias.service.controllers.RolController;
import agencias.service.models.dto.Request.RolDTO;
import agencias.service.models.dto.Response.RolResponseDTO;
import agencias.service.service.RolService;
import agencias.service.utils.RolUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RolTest {

    @Mock
    RolService service;

    @InjectMocks
    RolController controller;

    @Test
    void crearRolTestOK(){
        RolDTO argumentSut = RolUtils.rolDto();
        RolResponseDTO respuesta = new RolResponseDTO("Rol guardado con éxito");
        ResponseEntity<?> expected = new ResponseEntity<>(respuesta, HttpStatus.CREATED);

        when(service.save(argumentSut)).thenReturn(respuesta);
        ResponseEntity<?> actual = controller.save(argumentSut);

        assertEquals(expected, actual);
    }

    @Test
    void asignarRolTestOK(){
        Long id1 = 1L;
        Long id2 = 2L;
        RolResponseDTO respuesta = new RolResponseDTO("Rol guardado con éxito");
        ResponseEntity<?> expected = new ResponseEntity<>(respuesta, HttpStatus.OK);

        when(service.asignarRol(any(), any())).thenReturn(respuesta);
        ResponseEntity<?> actual = controller.asignarRol(id1, id2);

        assertEquals(expected, actual);
    }
}
