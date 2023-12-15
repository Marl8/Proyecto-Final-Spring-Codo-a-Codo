package agencias.service.controllerTest;

import agencias.service.controllers.AerolineaController;
import agencias.service.models.dto.Request.AerolineaRequestDTO;
import agencias.service.models.dto.Response.AerolineaResponseDTO;
import agencias.service.models.dto.Response.ResponseDeleteDto;
import agencias.service.service.AerolineaService;
import agencias.service.utils.AerolineaUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AerolineaTest {

    @Mock
    AerolineaService service;

    @InjectMocks
    AerolineaController controller;

    @Test
    @DisplayName("Listar aerolínea Camino Feliz")
    void listarAerolineasTestOk(){
        List<AerolineaRequestDTO> lista = AerolineaUtils.ListaAerolineasDto();
        ResponseEntity<?> expected = new ResponseEntity<>(lista, HttpStatus.OK);

        when(service.listarAerolinea()).thenReturn(lista);
        ResponseEntity<?> actual = controller.listarAerolinea();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Buscar aerolínea por Id Camino Feliz")
    void vueloPorIdTestOk(){
        Long id = 2L;
        AerolineaResponseDTO response = new AerolineaResponseDTO(AerolineaUtils.aereoDto1().getRazonSocial(),
                AerolineaUtils.aereoDto1().getCuit(),"Aerolinea encontrada!");
        ResponseEntity<?> expected = new ResponseEntity<>(response, HttpStatus.OK);

        when(service.traerAerolineaPorId(id)).thenReturn(response);
        ResponseEntity<?> actual = controller.traerAerolineaPorId(id);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Guardar aerolínea Camino Feliz")
    void guardarVueloTestOk(){
        AerolineaRequestDTO argumentSut = AerolineaUtils.aereoDto1();
        AerolineaResponseDTO response = new AerolineaResponseDTO(AerolineaUtils.aereoDto1().getRazonSocial(),
                AerolineaUtils.aereoDto1().getCuit(), "Aerolinea Guardada Correctamente!");
        ResponseEntity<?> expected = new ResponseEntity<>(response, HttpStatus.OK);

        when(service.guardarAerolinea(argumentSut)).thenReturn(response);
        ResponseEntity<?> actual = controller.guardarAerolinea(argumentSut);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Modificar aerolinea por Id Camino Feliz")
    void ModificarAerolineaTestOk(){
        Long id = 1L;
        AerolineaRequestDTO argumentSut = AerolineaUtils.aereoDto1();
        AerolineaResponseDTO response = new AerolineaResponseDTO(AerolineaUtils.aereoDto1().getRazonSocial(),
                AerolineaUtils.aereoDto1().getCuit(), "Aerolínea encontrada!");
        ResponseEntity<?> expected = new ResponseEntity<>(response, HttpStatus.OK);

        when(service.editarAerolinea(id, argumentSut)).thenReturn(response);
        ResponseEntity<?> actual = controller.editarAerolinea(id, argumentSut);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Eliminar aerolinea Camino Feliz")
    void eliminarAerolineaTestOk(){
        Long id = 1L;
        ResponseDeleteDto response = new ResponseDeleteDto("Aerolínea eliminada correctamente!");
        ResponseEntity<?> expected = new ResponseEntity<>(response, HttpStatus.OK);

        when(service.borrarAerolinea(id)).thenReturn(response);
        ResponseEntity<?> actual = controller.borrarAerolinea(id);

        assertEquals(expected, actual);
    }
}
