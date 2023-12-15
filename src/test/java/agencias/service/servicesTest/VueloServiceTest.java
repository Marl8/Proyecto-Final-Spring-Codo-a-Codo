package agencias.service.servicesTest;

import agencias.service.exceptions.VueloGenericException;
import agencias.service.models.dto.Request.VueloRequestDTO;
import agencias.service.models.dto.Response.AerolineaResponseDTO;
import agencias.service.models.dto.Response.ResponseDeleteDto;
import agencias.service.models.dto.Response.VueloDTO;
import agencias.service.models.dto.Response.VueloResponseDTO;
import agencias.service.models.entity.Aerolinea;
import agencias.service.models.entity.Vuelo;
import agencias.service.repository.AerolineaRepository;
import agencias.service.repository.VueloRepository;
import agencias.service.service.impl.VueloServiceImpl;
import agencias.service.utils.AerolineaUtils;
import agencias.service.utils.VueloUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VueloServiceTest {

    @Mock
    VueloRepository repository;

    @Mock
    AerolineaRepository aerolineaRepository;

    @InjectMocks
    VueloServiceImpl service;

    @Test
    @DisplayName("Test guardar vuelo camino feliz")
    void guardarVueloTestOk(){
        VueloRequestDTO vueloDto = VueloUtils.vueloDTO();
        Vuelo argumentSut = VueloUtils.vuelo1();
        VueloResponseDTO expected = new VueloResponseDTO(VueloUtils.vueloRespuestaDTO(), "Vuelo Guardado Correctamente!");

        when(aerolineaRepository.findById(any())).thenReturn(Optional.of(AerolineaUtils.aereo1()));
        when(repository.save(any())).thenReturn(argumentSut);
        VueloResponseDTO actual = service.crearVuelo(vueloDto);

        assertEquals(expected.getMensaje(), actual.getMensaje());
        assertEquals(expected.getVueloDto().getNumVuelo(), actual.getVueloDto().getNumVuelo());
    }

    @Test
    @DisplayName("Test listar vuelos camino feliz")
    void listarVuelosTestOk(){
        List<Vuelo> argumentSut = VueloUtils.ListaVuelos();
        List<VueloDTO> expected = VueloUtils.listaRespuestaDTO();

        when(repository.findAll()).thenReturn(argumentSut);
        List<VueloDTO> actual = service.mostrarVuelos();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    @DisplayName("Test buscar vuelo por id camino feliz")
    void listarVuelosByIdTestOk(){
        Long id = 1L;
        Vuelo argumentSut = VueloUtils.vuelo1();
        VueloResponseDTO expected = new VueloResponseDTO(VueloUtils.vueloRespuestaDTO(), "Vuelo encontrado!");

        when(repository.findById(any())).thenReturn(Optional.of(argumentSut));

        VueloResponseDTO actual = service.vueloPorId(id);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test buscar vuelo por id lanza EXCEPTION")
    void listarVueloByIdTestEXCEPTION(){
        Long id = 1L;
        when(repository.findById(any())).thenReturn(Optional.empty());
        VueloGenericException expected = new VueloGenericException("No existe vuelo con ese id!");

        VueloGenericException actual = assertThrows(VueloGenericException.class, () -> service.vueloPorId(id));
        assertEquals(actual.getMessage(), expected.getMessage());
    }

    @Test
    @DisplayName("Test modificar vuelos camino feliz")
     void modificarVueloTestOk(){
        Long id = 1L;
        VueloRequestDTO dto = VueloUtils.vueloDTO();
        Vuelo argumentSut = VueloUtils.vuelo1();
        Vuelo modificado = VueloUtils.vueloModificado();
        VueloResponseDTO expected = new VueloResponseDTO(VueloUtils.vueloModificadoDTO(), "Vuelo modificado correctamente.");

        when(aerolineaRepository.findById(any())).thenReturn(Optional.of(AerolineaUtils.aereo1()));
        when(repository.findById(any())).thenReturn(Optional.of(argumentSut));
        when(aerolineaRepository.findById(any())).thenReturn(Optional.of(AerolineaUtils.aereo1()));
        when(repository.save(any())).thenReturn(modificado);

        VueloResponseDTO actual = service.editarVuelo(id, dto);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test modificar vuelo lanza EXCEPTION")
    void modificarVueloTestEXCEPTION(){
        Long id = 1L;
        VueloRequestDTO dto = VueloUtils.vueloDTO();
        when(repository.findById(any())).thenReturn(Optional.empty());
        VueloGenericException expected = new VueloGenericException("No se encontró el vuelo a modificar");

        VueloGenericException actual = assertThrows(VueloGenericException.class, () -> service.editarVuelo(id, dto));
        assertEquals(actual.getMessage(), expected.getMessage());
    }

    @Test
    @DisplayName("Test eliminar camino feliz")
    void eliminarVueloTestOK(){
        Long id = 1L;
        Vuelo argumentSut = VueloUtils.vuelo1();
        ResponseDeleteDto expected = new ResponseDeleteDto("Vuelo eliminado correctamente!");
        when(repository.findById(any())).thenReturn(Optional.of(argumentSut));

        ResponseDeleteDto actual = service.eliminarVueloPorId(id);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test eliminar vuelo lanza EXCEPTION")
    void eliminarVueloTestEXCEPTION(){
        Long id = 1L;
        when(repository.findById(any())).thenReturn(Optional.empty());
        VueloGenericException expected = new VueloGenericException("No encontramos el vuelo a eliminar!");

        VueloGenericException actual = assertThrows(VueloGenericException.class, () -> service.eliminarVueloPorId(id));
        assertEquals(actual.getMessage(), expected.getMessage());
    }
}
