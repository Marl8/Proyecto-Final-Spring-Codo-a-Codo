package agencias.service.controllerTest;

import agencias.service.controllers.ReservaController;
import agencias.service.models.dto.Response.ReporteResponseDTO;
import agencias.service.models.dto.Response.ReservasByUserResponseDTO;
import agencias.service.models.entity.Usuario;
import agencias.service.service.ReservaService;
import agencias.service.utils.ReservaUtils;
import agencias.service.utils.UsuarioUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReservaTest {

    @Mock
    ReservaService service;

    @InjectMocks
    ReservaController controller;

    @Test
    void generarReporteTestOK(){
        LocalDate date1 = LocalDate.of(2023, 3, 9);
        LocalDate date2 = LocalDate.of(2023, 12, 18);

        Map<String, Integer> destinosPopulares = new HashMap<>();
        destinosPopulares.put("Rio Janeiro", 1);
        destinosPopulares.put("Paris", 1);
        ReporteResponseDTO respuesta = new ReporteResponseDTO(3, 1000D,
                destinosPopulares);
        ResponseEntity<?> expected = new ResponseEntity<>(respuesta, HttpStatus.OK);

        when(service.generarReporte(date1, date2)).thenReturn(respuesta);
        ResponseEntity<?> actual = controller.generarReporte(date1, date2);

        assertEquals(expected, actual);
    }

    @Test
    void generarReportePorFechaTestOK(){
        LocalDate date1 = LocalDate.of(2023, 3, 9);

        Map<String, Integer> destinosPopulares = new HashMap<>();
        destinosPopulares.put("Rio Janeiro", 1);
        destinosPopulares.put("Paris", 1);
        ReporteResponseDTO respuesta = new ReporteResponseDTO(3, 1000D,
                destinosPopulares);
        ResponseEntity<?> expected = new ResponseEntity<>(respuesta, HttpStatus.OK);

        when(service.generarReporteUnaFecha(date1)).thenReturn(respuesta);
        ResponseEntity<?> actual = controller.reporteParaUnaFecha(date1);

        assertEquals(expected, actual);
    }

    @Test
    void TicketPorIdTestOK(){
        Usuario usuario = UsuarioUtils.usuario1();
        ReservasByUserResponseDTO respuesta = new ReservasByUserResponseDTO(
                "Reservas efectuadas por " + usuario.getNombre() + usuario.getApellido() + ": ",
                ReservaUtils.listaReservasByDto());
        ResponseEntity<?> expected = new ResponseEntity<>(respuesta, HttpStatus.OK);

        when(service.reservasByUsuario(usuario.getIdUsuario())).thenReturn(respuesta);
        ResponseEntity<?> actual = controller.reservasByUser(usuario.getIdUsuario());

        assertEquals(expected, actual);
    }
}
