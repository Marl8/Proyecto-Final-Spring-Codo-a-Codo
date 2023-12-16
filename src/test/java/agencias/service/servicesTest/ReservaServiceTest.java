package agencias.service.servicesTest;

import agencias.service.exceptions.CustomException;
import agencias.service.exceptions.ReservaNotFoundException;
import agencias.service.models.dto.Request.ReservaRequestDTO;
import agencias.service.models.dto.Response.ReporteResponseDTO;
import agencias.service.models.dto.Response.ReservaResponseDTO;
import agencias.service.models.dto.Response.ReservasByUserResponseDTO;
import agencias.service.models.entity.Reserva;
import agencias.service.models.entity.Usuario;
import agencias.service.models.entity.Vuelo;
import agencias.service.repository.ReservaRepository;
import agencias.service.repository.UsuarioRepository;
import agencias.service.repository.VueloRepository;
import agencias.service.service.impl.ReservaServiceImpl;
import agencias.service.utils.ReservaUtils;
import agencias.service.utils.UsuarioUtils;
import agencias.service.utils.VueloUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    @Mock
    ReservaRepository repository;

    @Mock
    VueloRepository vueloRepository;

    @Mock
    UsuarioRepository userRepository;

    @InjectMocks
    ReservaServiceImpl service;

    @Test
    void reservaGenerarReporteTest(){
        LocalDate date1 = LocalDate.of(2023, 10, 10);
        LocalDate date2 = LocalDate.of(2023, 12, 31);
        int vuelosVendidos = 2;
        Double ingresosGenerados = 2000D;
        Map<String, Integer> destinosPopulares = new HashMap<>();
        destinosPopulares.put("Rio Janeiro", 1);
        destinosPopulares.put("Paris", 1);
        List<Reserva> listaReservas = ReservaUtils.listaReservas();
        ReporteResponseDTO expected = new ReporteResponseDTO(vuelosVendidos, ingresosGenerados, destinosPopulares);

        when(repository.findAll()).thenReturn(listaReservas);

        ReporteResponseDTO actual = service.generarReporte(date1, date2);

        assertEquals(expected, actual);
    }

    @Test
    void reservaGenerarReportePorFechaTest(){
        LocalDate date = LocalDate.of(2023, 12, 11);
        int vuelosVendidos = 2;
        Double ingresosGenerados = 2000D;
        Map<String, Integer> destinosPopulares = new HashMap<>();
        destinosPopulares.put("Rio Janeiro", 1);
        destinosPopulares.put("Paris", 1);
        List<Reserva> listaReservas = ReservaUtils.listaReservas();
        ReporteResponseDTO expected = new ReporteResponseDTO(vuelosVendidos, ingresosGenerados, destinosPopulares);

        when(repository.findAll()).thenReturn(listaReservas);

        ReporteResponseDTO actual = service.generarReporteUnaFecha(date);

        assertEquals(expected, actual);
    }

    @Test
    void reservasByUsuario(){
        Usuario usuario = UsuarioUtils.usuario1();
        List<Reserva> lista = ReservaUtils.listaReservas();
        ReservasByUserResponseDTO expected = new ReservasByUserResponseDTO(
                "Reservas efectuadas por " + usuario.getNombre() + " " + usuario.getApellido() + ": ",
        ReservaUtils.listaReservasByDto());

        when(userRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(repository.findReservaByIdUsuario(any())).thenReturn(lista);

        ReservasByUserResponseDTO actual = service.reservasByUsuario(usuario.getIdUsuario());

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test buscar reservas by usuario por id lanza EXCEPTION")
    void reservasByUsuarioTestEXCEPTION(){
        Long id = 1L;
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        ReservaNotFoundException expected = new ReservaNotFoundException("No existen usuarios con ese id");

        ReservaNotFoundException actual = assertThrows(ReservaNotFoundException.class, () -> service.reservasByUsuario(id));
        assertEquals(actual.getMessage(), expected.getMessage());
    }

    @Test
    @DisplayName("Test OK para crear reservas")
    void crearReservaTestOK(){
        ReservaRequestDTO entrada = ReservaUtils.reservaDto1();
        Reserva reserva = ReservaUtils.reserva1();
        Vuelo vuelo = VueloUtils.vuelo3();
        Usuario usuario = UsuarioUtils.usuario1();
        ReservaResponseDTO expected = new ReservaResponseDTO("Su reserva ha sido exitosa. Precio con descuento", "Vuelo con salida de " +
                reserva.getVuelo().getItinerario().getCiudadOrigen() + " con destino a "
                + reserva.getVuelo().getItinerario().getCiudadDestino(), 81000D, reserva.getTipoPago(),
                reserva.getFechaReserva());

        when(vueloRepository.findById(any())).thenReturn(Optional.of(vuelo));
        when(userRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(repository.save(any())).thenReturn(reserva);

        ReservaResponseDTO actual = service.crearReserva(entrada);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName(value = "Test lanzar EXCEPTION en update por no encontrar vuelo asociado")
    void UpdateTicketTestEXCEPTION(){
        ReservaRequestDTO entrada = ReservaUtils.reservaDto1();
        CustomException expected = new CustomException(HttpStatus.NOT_FOUND, "No existe el vuelo que intenta reservar");
        when(vueloRepository.findById(any())).thenReturn(Optional.empty());

        CustomException actual = assertThrows(CustomException.class, () -> service.crearReserva(entrada));
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Test
    @DisplayName(value = "Test lanzar EXCEPTION en update por no encontrar usuario asociado")
    void UpdateTicketTestEXCEPTION2(){
        ReservaRequestDTO entrada = ReservaUtils.reservaDto1();
        Vuelo vuelo = VueloUtils.vuelo3();
        CustomException expected = new CustomException(HttpStatus.NOT_FOUND, "No existe el usuario con el que intenta reservar");
        when(vueloRepository.findById(any())).thenReturn(Optional.of(vuelo));
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        CustomException actual = assertThrows(CustomException.class, () -> service.crearReserva(entrada));
        assertEquals(expected.getMessage(), actual.getMessage());
    }
}
