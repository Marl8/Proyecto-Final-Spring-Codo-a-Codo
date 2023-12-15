package agencias.service.controllers;

import agencias.service.exceptions.CustomException;
import agencias.service.models.dto.Request.UsuarioRequestDTO;
import agencias.service.service.UsuarioService;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuario")
    public ResponseEntity<?> altaUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) throws CustomException{
        try {
            return new ResponseEntity<>(usuarioService.createUsuario(usuarioRequestDTO), HttpStatus.CREATED);
        } catch (CustomException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }

    }

}
