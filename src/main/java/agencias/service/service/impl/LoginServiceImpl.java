package agencias.service.service.impl;

import agencias.service.models.dto.Request.LoginDto;
import agencias.service.models.dto.Response.JwtDto;
import agencias.service.models.entity.Usuario;
import agencias.service.repository.UsuarioRepository;
import agencias.service.service.LoginService;
import agencias.service.service.securityServices.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    AuthenticationManager authenticationManager;

    UsuarioRepository repository;

    JwtService jwtService;

    public LoginServiceImpl(AuthenticationManager authenticationManager,
                            UsuarioRepository repository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.jwtService = jwtService;
    }

    @Override
    public JwtDto login(LoginDto loginDto) {

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword());
        authenticationManager.authenticate(auth);

        Usuario user = repository.findByUsername(loginDto.getUsername()).get();
        String jwt = jwtService.generate(user, generateExtraClaims(user));
        return new JwtDto(jwt);
    }

    private Map<String, Object> generateExtraClaims(Usuario user) {
        Map<String, Object> extraClaims = new HashMap<>();

        // Obtengo el nombre real de Usuario
        extraClaims.put("name", user.getNombre() + " " + user.getApellido());
        // Obtengo la lista de roles asignados al usuario
        extraClaims.put("roles", user.getRoles().stream()
                .map(rol -> rol.getName().name()).toList());
        return extraClaims;
    }
}
