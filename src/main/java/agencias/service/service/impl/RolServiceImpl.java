package agencias.service.service.impl;
import agencias.service.exceptions.RolGenericException;
import agencias.service.models.dto.Request.RolDTO;
import agencias.service.models.dto.Response.RolResponseDTO;
import agencias.service.models.entity.Rol;
import agencias.service.models.entity.Usuario;
import agencias.service.repository.RolRepository;
import agencias.service.repository.UsuarioRepository;
import agencias.service.service.RolService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class RolServiceImpl implements RolService {

    RolRepository repository;

    UsuarioRepository usRepository;

    public RolServiceImpl(RolRepository repository, UsuarioRepository usRepo) {
        this.repository = repository;
        this.usRepository = usRepo;
    }

    @Override
    public RolResponseDTO save(RolDTO rolDto) {

        List<Rol> roles = repository.findAll();
        ModelMapper mapper = new ModelMapper();
        if(!roles.isEmpty()){
            for(Rol role : roles) {
                if(role.getName().name().equals(rolDto.getName().name())) {
                    throw new RolGenericException("Role " + role.getName() + " ya existe");
                }
            }
        }
        Rol role = mapper.map(rolDto, Rol.class);
        repository.save(role);
        return new RolResponseDTO("Rol guardado con éxito");
    }

    @Override
    public RolResponseDTO asignarRol(Long idRol, Long idUsuario) {
        Usuario usuario = usRepository.findById(idUsuario).orElseThrow(
                () -> new RuntimeException("No existen usuarios con este id"));
        Rol rol = repository.findById(idRol).orElseThrow(
                () -> new RolGenericException("No existen roles con este id"));
        Set<Rol> nuevosRoles = usuario.getRoles();
        for (Rol r : nuevosRoles) {
            if(r.getName().name().equals(rol.getName().name())) {
                throw new RolGenericException("Role " + r.getName() + " el usuario ya posee este rol");
            }
        }
        nuevosRoles.add(rol);
        usuario.setRoles(nuevosRoles);
        usRepository.save(usuario);
        return new RolResponseDTO("Rol asignado con éxito");
    }

    @Override
    public List<RolDTO> findAll() {
        List<Rol> lista = repository.findAll();
        if(lista.isEmpty()) {
            throw new RolGenericException("No se han encontrado roles");
        }
        ModelMapper mapper = new ModelMapper();
        return lista.stream()
                .map(r -> mapper.map(r, RolDTO.class))
                .toList();
    }
}
