package agencias.service.controllerTest;

import agencias.service.controllers.VueloController;
import agencias.service.models.dto.Request.VueloRequestDTO;
import agencias.service.models.dto.Response.ResponseDeleteDto;
import agencias.service.models.dto.Response.VueloDTO;
import agencias.service.models.dto.Response.VueloResponseDTO;
import agencias.service.service.VueloService;
import agencias.service.utils.VueloUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VueloTest {

    @Mock
    VueloService service;
    @InjectMocks
    VueloController controller;

    @Test
    @DisplayName("Guardar Vuelo Camino Feliz")
    void guardarVueloTestOk(){
        VueloRequestDTO argumentSut = VueloUtils.vueloDTO();
        VueloResponseDTO response = new VueloResponseDTO(VueloUtils.vueloRespuestaDTO(), "Vuelo Guardado Correctamente!");
        ResponseEntity<?> expected = new ResponseEntity<>(response, HttpStatus.OK);

        when(service.crearVuelo(argumentSut)).thenReturn(response);
        ResponseEntity<?> actual = controller.guardarVuelo(argumentSut);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Buscar Vuelo por Id Camino Feliz")
    void vueloPorIdTestOk(){
        Long id = 2L;
        VueloResponseDTO response = new VueloResponseDTO(VueloUtils.vueloRespuestaDTO2(), "Vuelo encontrado!");
        ResponseEntity<?> expected = new ResponseEntity<>(response, HttpStatus.OK);

        when(service.vueloPorId(id)).thenReturn(response);
        ResponseEntity<?> actual = controller.traerVuelo(id);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Modificar Vuelo por Id Camino Feliz")
    void ModificarVueloTestOk(){
        Long id = 1L;
        VueloRequestDTO argumentSut = VueloUtils.vueloDTO();
        VueloResponseDTO response = new VueloResponseDTO(VueloUtils.vueloModificadoDTO(), "Vuelo encontrado!");
        ResponseEntity<?> expected = new ResponseEntity<>(response, HttpStatus.OK);

        when(service.editarVuelo(id, argumentSut)).thenReturn(response);
        ResponseEntity<?> actual = controller.modificarVuelo(id, argumentSut);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Eliminar Vuelo Camino Feliz")
    void eliminarVueloTestOk(){
        Long id = 1L;
        ResponseDeleteDto response = new ResponseDeleteDto("Vuelo eliminado correctamente!");
        ResponseEntity<?> expected = new ResponseEntity<>(response, HttpStatus.OK);

        when(service.eliminarVueloPorId(id)).thenReturn(response);
        ResponseEntity<?> actual = controller.eliminarVuelo(id);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Listar Vuelos Camino Feliz")
    void listarVuelosTestOk(){
        List<VueloDTO> listaVuelos = VueloUtils.listaRespuestaDTO();
        ResponseEntity<?> expected = new ResponseEntity<>(listaVuelos, HttpStatus.OK);

        when(service.mostrarVuelos()).thenReturn(listaVuelos);
        ResponseEntity<?> actual = controller.listarVuelos();

        assertEquals(expected, actual);
    }
}
