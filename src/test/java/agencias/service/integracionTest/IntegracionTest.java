package agencias.service.integracionTest;

import agencias.service.models.dto.Request.LoginDto;
import agencias.service.models.dto.Request.UsuarioRequestDTO;
import agencias.service.models.entity.Rol;
import agencias.service.models.entity.Usuario;
import agencias.service.models.enums.ERol;
import agencias.service.service.securityServices.jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class IntegracionTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JwtService jwtService;

    @Test
    void loginServicesOktest() throws Exception {

        Rol rol = new Rol(1L, ERol.ADMIN);
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);

        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setUsername("prueba");
        usuario.setPassword("123");
        usuario.setRoles(roles);

        LoginDto dto = new LoginDto();
        dto.setUsername("prueba");
        dto.setPassword("123");

        ObjectMapper obMapper = new ObjectMapper();
        obMapper.registerModule(new JavaTimeModule());
        ObjectWriter mapper = obMapper
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payload = mapper.writeValueAsString(dto);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", usuario.getNombre() + " " + usuario.getApellido());
        extraClaims.put("roles", usuario.getRoles().stream()
                .map(r -> r.getName().name()).toList());
        String token = jwtService.generate(usuario, extraClaims);

        MvcResult response = mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                        .header("Authorization", "Bearer " + token))
                .andDo(print()) // imprime por consola el request y él response
                .andExpect(status().isOk())
                .andReturn();
    }



   /* @Test
    void usuarioGuardarOKTest() throws Exception{

        Rol rol = new Rol(1L, ERol.ADMIN);
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);

        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setUsername("prueba");
        usuario.setPassword("123");
        usuario.setRoles(roles);

        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNombre("Jorge");
        dto.setApellido("Lorenzo");
        dto.setDni(27950105L);
        dto.setFechaNacimiento(LocalDate.of(1978, 10, 9));
        dto.setEmail("jlorenzo@gmail.com");
        dto.setTelefono(114985233L);
        dto.setUsername("lorenzo");
        dto.setPassword("123");

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", usuario.getNombre());
        extraClaims.put("roles", usuario.getRoles().stream()
                .map(r -> r.getName().name()).toList());
        String token = jwtService.generate(usuario,extraClaims);

        ObjectMapper obMapper = new ObjectMapper();
        obMapper.registerModule(new JavaTimeModule());
        ObjectWriter mapper = obMapper
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payload = mapper.writeValueAsString(dto);

        mockMvc.perform(post( "/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                        .header("Authorization", "Bearer " + token))
                .andDo(print()) // imprime por consola el request y él response
                .andExpect(status().isOk())
                .andReturn();
    }*/

}