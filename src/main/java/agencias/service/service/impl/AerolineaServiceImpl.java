package agencias.service.service.impl;

import agencias.service.exceptions.AerolineaNotFoundException;
import agencias.service.exceptions.VueloGenericException;
import agencias.service.models.dto.Request.AerolineaRequestDTO;
import agencias.service.models.dto.Request.VueloRequestDTO;
import agencias.service.models.dto.Response.AerolineaResponseDTO;
import agencias.service.models.dto.Response.ResponseDeleteDto;
import agencias.service.models.dto.Response.VueloResponseDTO;
import agencias.service.models.entity.Aerolinea;
import agencias.service.models.entity.Vuelo;
import agencias.service.repository.AerolineaRepository;
import agencias.service.service.AerolineaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AerolineaServiceImpl  implements AerolineaService {
    AerolineaRepository repoLinea;
    private final ModelMapper mapper;

    public AerolineaServiceImpl(AerolineaRepository repoLinea) {
        this.repoLinea = repoLinea;
        mapper = new ModelMapper();
    }

    @Override
    public AerolineaResponseDTO guardarAerolinea(AerolineaRequestDTO aerolinea) {
        /*Aca con mapper convierto aerolinea en una clase y la guardo en aerolineaClass */
        Aerolinea aerolineaClass = mapper.map(aerolinea, Aerolinea.class);
        /* hacer el save de instancia aerolineaClass*/
        Aerolinea aerolineaGuardada = repoLinea.save(aerolineaClass);
        /* retorno el DTO de respuesta y el mensaje correspondiente*/
        return new AerolineaResponseDTO(aerolineaGuardada.getRazonSocial(),
                aerolineaGuardada.getCuit(), " Se guardo correctamente");
    }

    @Override
    public AerolineaResponseDTO editarAerolinea(Long id, AerolineaRequestDTO aereoDto) {
        Aerolinea aereoExistente = repoLinea.findById(id).orElseThrow(() -> new AerolineaNotFoundException("No se encontró la aerolínea a modificar"));

        ModelMapper modelMapper = new ModelMapper();

        Aerolinea aereoEditado = modelMapper.map(aereoDto, Aerolinea.class);

        aereoExistente.setRazonSocial(aereoEditado.getRazonSocial());
        aereoExistente.setCuit(aereoEditado.getCuit());

        Aerolinea a = repoLinea.save(aereoExistente);
        return new AerolineaResponseDTO(a.getRazonSocial(), a.getCuit(), " se modificó correctamente");
    }
    @Override
    public ResponseDeleteDto borrarAerolinea(Long idAerolinea) {
        Aerolinea aerolinea = repoLinea.findById(idAerolinea).orElseThrow(() -> {
            throw new AerolineaNotFoundException("No existe la Aerolinea que desea eliminar");
        });
        String nombreAerolineaEliminada = aerolinea.getRazonSocial(); // Obtener el nombre de la aerolínea eliminada
        repoLinea.deleteById(idAerolinea);/*delete de la aerolinea*/
        /* aca quiero mostrar los datos de la aerolinea que se borro pero solo pude mostrar la razonSocial*/
        return new ResponseDeleteDto(nombreAerolineaEliminada + " Se elimino correctamente ");
    }

    @Override
    public AerolineaResponseDTO traerAerolineaPorId(Long idAerolinea) {
        Aerolinea aerolinea = repoLinea.findById(idAerolinea)
                .orElseThrow(() -> new AerolineaNotFoundException("No existe la Aerolínea que desea buscar"));

        return new AerolineaResponseDTO(aerolinea.getRazonSocial(),
                aerolinea.getCuit(), "Se encontró la Aerolínea buscada");
    }

    @Override
    public List<AerolineaRequestDTO> listarAerolinea() {
    List<Aerolinea> aerolineas = repoLinea.findAll();

    if (aerolineas.isEmpty()){
        throw new AerolineaNotFoundException("No existen Aerolineas para listar");
    }

    ModelMapper modelMapper = new ModelMapper();
    List<AerolineaRequestDTO> responseDTOs = new ArrayList<>();

    for (Aerolinea aerolinea : aerolineas) {
        AerolineaRequestDTO responseDTO = modelMapper.map(aerolinea, AerolineaRequestDTO.class);
        responseDTOs.add(responseDTO);
    }

    return responseDTOs;
    }

}




