package agencias.service.servicesTest;

import agencias.service.exceptions.AerolineaNotFoundException;
import agencias.service.models.dto.Request.AerolineaRequestDTO;
import agencias.service.models.dto.Response.AerolineaResponseDTO;
import agencias.service.models.dto.Response.ResponseDeleteDto;
import agencias.service.models.entity.Aerolinea;
import agencias.service.repository.AerolineaRepository;
import agencias.service.service.impl.AerolineaServiceImpl;
import agencias.service.utils.AerolineaUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AerolineaServiceTest {

    @Mock
    AerolineaRepository repository;

    @InjectMocks
    AerolineaServiceImpl service;

    @Test
    @DisplayName(value = "Test OK para update de aerolinea")
    void aerolineaUpdateTestOK(){
        AerolineaRequestDTO dto = AerolineaUtils.aereoDto1();
        Long id = 1L;
        Aerolinea argumentSut = AerolineaUtils.aereo1();
        Aerolinea modificado = AerolineaUtils.aereoModificado1();
        AerolineaResponseDTO expected = new AerolineaResponseDTO(AerolineaUtils.aereoModificadoDto1().getRazonSocial(),
                AerolineaUtils.aereoModificadoDto1().getCuit(), " se modificó correctamente");

        when(repository.findById(any())).thenReturn(Optional.of(argumentSut));
        when(repository.save(any())).thenReturn(modificado);

        AerolineaResponseDTO actual = service.editarAerolinea(id, dto);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName(value = "Test lanzar EXCEPTION en update por no encontrar una aerolínea asociada")
    void updateAerolineaTestEXCEPTION(){
        Long id = 1L;
        AerolineaRequestDTO dto = AerolineaUtils.aereoDto1();
        AerolineaNotFoundException expected = new AerolineaNotFoundException ("No se encontró la aerolínea a modificar");
        when(repository.findById(any())).thenReturn(Optional.empty());
        AerolineaNotFoundException actual = assertThrows(AerolineaNotFoundException.class, () -> service.editarAerolinea(id, dto));
        assertEquals(actual.getMessage(), expected.getMessage());
    }

    @Test
    @DisplayName(value = "Test OK para guardar una aerolínea")
    void aerolineaSaveTestOK(){
        AerolineaRequestDTO dto = AerolineaUtils.aereoDto1();
        Aerolinea argumentSut = AerolineaUtils.aereo1();
        AerolineaResponseDTO expected = new AerolineaResponseDTO(dto.getRazonSocial(), dto.getCuit(),
                " Se guardo correctamente");

        when(repository.save(any())).thenReturn(argumentSut);

        AerolineaResponseDTO actual = service.guardarAerolinea(dto);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName(value = "Test OK de eliminar una aerolínea por id")
    void eliminarAereolineaPorIdTestOK(){
        Long id = 1L;
        Aerolinea argumentSut = AerolineaUtils.aereo1();
        String mensaje = AerolineaUtils.aereoDto3().getRazonSocial() + " Se elimino correctamente ";
        ResponseDeleteDto expected = new ResponseDeleteDto(mensaje);

        when(repository.findById(any())).thenReturn(Optional.of(argumentSut));
        repository.deleteById(1L);
        ResponseDeleteDto actual = service.borrarAerolinea(argumentSut.getIdAerolinea());

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName(value = "Test lanzar EXCEPTION en eliminar por no encontrar una aerolínea asociada")
    void eliminarAerolineaPorIdTestEXCEPTION(){
        Long id = 1L;
        AerolineaNotFoundException expected = new AerolineaNotFoundException ("No existe la Aerolinea que desea eliminar");
        when(repository.findById(any())).thenReturn(Optional.empty());

        AerolineaNotFoundException actual = assertThrows(AerolineaNotFoundException.class, () -> service.borrarAerolinea(id));
        assertEquals(actual.getMessage(), expected.getMessage());
    }

    @Test
    @DisplayName(value = "Test OK de buscar una aerolínea por id")
    void aerolineaPorIdTestOK(){
        Long id = 1L;
        Aerolinea argumentSut = AerolineaUtils.aereo1();
        AerolineaResponseDTO expected = new AerolineaResponseDTO(AerolineaUtils.aereoDto1().getRazonSocial(),
                AerolineaUtils.aereoDto1().getCuit(), "Se encontró la Aerolínea buscada");

        when(repository.findById(any())).thenReturn(Optional.of(argumentSut));
        AerolineaResponseDTO actual = service.traerAerolineaPorId(id);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName(value = "Test lanzar EXCEPTION por no encontrar una aerolínea asociada")
    void buscarAerolineaPorIdTestEXCEPTION(){
        Long id = 1L;
        AerolineaNotFoundException expected = new AerolineaNotFoundException ("No existe la Aerolínea que desea buscar");
        when(repository.findById(any())).thenReturn(Optional.empty());

        AerolineaNotFoundException actual = assertThrows(AerolineaNotFoundException.class, () -> service.traerAerolineaPorId(id));
        assertEquals(actual.getMessage(), expected.getMessage());
    }

    @Test
    @DisplayName(value = "Test OK para find All aerolinea")
    void findAllAerolineasTestOK(){
        List<Aerolinea> argumentSut = AerolineaUtils.ListaAerolineas();
        List<AerolineaRequestDTO> expected = AerolineaUtils.ListaAerolineasDto();

        when(repository.findAll()).thenReturn(argumentSut);
        List<AerolineaRequestDTO> actual = service.listarAerolinea();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    @DisplayName(value = "Test lanzar EXCEPTION por no encontrar aerolíneas")
    void findAllAerolineasTestEXCEPTION(){

        AerolineaNotFoundException expected = new AerolineaNotFoundException("No existen Aerolineas para listar");
        List<Aerolinea> argumentSut = new ArrayList<>();
        when(repository.findAll()).thenReturn(argumentSut);

        AerolineaNotFoundException actual = assertThrows(AerolineaNotFoundException.class, () -> service.listarAerolinea());
        assertEquals(actual.getMessage(), expected.getMessage());
    }
}
