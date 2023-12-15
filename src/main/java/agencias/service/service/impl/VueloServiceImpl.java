package agencias.service.service.impl;

import agencias.service.exceptions.AerolineaNotFoundException;
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
import agencias.service.service.VueloService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VueloServiceImpl implements VueloService {

    VueloRepository vueloRepository;

    AerolineaRepository aerolineaRepository;

    public VueloServiceImpl(VueloRepository vueloRepository, AerolineaRepository aereo){
        this.vueloRepository = vueloRepository;
        this.aerolineaRepository = aereo;
    }

    @Override
    public List<VueloDTO> mostrarVuelos() {
        List<Vuelo> listaVuelos = vueloRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        List<VueloDTO> vuelosDtoList = new ArrayList<>();

        listaVuelos.forEach(vuelo -> vuelosDtoList.add(modelMapper.map(vuelo, VueloDTO.class)));

        return vuelosDtoList;
    }

    @Override
    public VueloResponseDTO crearVuelo(VueloRequestDTO vueloRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();

        Vuelo nuevoVuelo = vueloMapeado(vueloRequestDTO);
        Aerolinea aerolinea = aerolineaRepository.findById(vueloRequestDTO.getIdAerolinea())
                .orElseThrow(() -> new AerolineaNotFoundException("No existen aerolíneas con ese id"));

        nuevoVuelo.setAerolinea(aerolinea);
        System.out.println(nuevoVuelo);
        Vuelo vueloPersist = vueloRepository.save(nuevoVuelo);

        VueloDTO dto = modelMapper.map(vueloPersist, VueloDTO.class);
        return new VueloResponseDTO(dto, "Vuelo Guardado Correctamente!");
    }

    public Vuelo vueloMapeado(VueloRequestDTO vueloDto){
        Vuelo vuelo = new Vuelo();
        vuelo.setNumVuelo(vueloDto.getNumVuelo());
        vuelo.setFecha(vueloDto.getFecha());
        vuelo.setItinerario(vueloDto.getItinerario());
        vuelo.setCantAsientos(vueloDto.getCantAsientos());
        vuelo.setDisponibilidad(vueloDto.isDisponibilidad());
        return vuelo;
    }

    @Override
    public VueloResponseDTO vueloPorId(Long idVuelo) {
        ModelMapper modelMapper = new ModelMapper();

        Vuelo vuelo = vueloRepository.findById(idVuelo).orElseThrow( () -> new VueloGenericException("No existe vuelo con ese id!"));

        VueloDTO response = modelMapper.map(vuelo, VueloDTO.class);
        VueloResponseDTO vueloResponseDTO = new VueloResponseDTO();
        vueloResponseDTO.setMensaje("Vuelo encontrado!");
        vueloResponseDTO.setVueloDto(response);

        return vueloResponseDTO;
    }

    @Override
    public VueloResponseDTO editarVuelo(Long idVuelo, VueloRequestDTO vueloRequestDTO) {
        Vuelo vueloExistente = vueloRepository.findById(idVuelo).orElseThrow(() -> new VueloGenericException("No se encontró el vuelo a modificar"));
        Aerolinea aerolinea = aerolineaRepository.findById(vueloRequestDTO.getIdAerolinea())
                .orElseThrow(() -> new AerolineaNotFoundException("No existen aerolíneas con ese id"));
        ModelMapper modelMapper = new ModelMapper();

        Vuelo vueloEditado = modelMapper.map(vueloRequestDTO, Vuelo.class);

        vueloExistente.setNumVuelo(vueloEditado.getNumVuelo());
        vueloExistente.setCantAsientos(vueloEditado.getCantAsientos());
        vueloExistente.setDisponibilidad(vueloEditado.isDisponibilidad());
        vueloExistente.setFecha(vueloEditado.getFecha());
        vueloExistente.setAerolinea(aerolinea);
        vueloExistente.setItinerario(vueloEditado.getItinerario());

        System.out.println(vueloExistente);
        Vuelo v =vueloRepository.save(vueloExistente);
        VueloDTO response = modelMapper.map(v, VueloDTO.class);
        return new VueloResponseDTO(response, "Vuelo modificado correctamente.");
    }

    @Override
    public ResponseDeleteDto eliminarVueloPorId(Long idVuelo) {
        vueloRepository.findById(idVuelo).orElseThrow(() -> new VueloGenericException("No encontramos el vuelo a eliminar!"));
        vueloRepository.deleteById(idVuelo);
        return new ResponseDeleteDto("Vuelo eliminado correctamente!");
    }


}
