package agencias.service.controllers;

import agencias.service.models.dto.Request.LoginDto;
import agencias.service.service.LoginService;
import agencias.service.service.impl.LoginServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    LoginService service;

    public LoginController(LoginServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(service.login(loginDto), HttpStatus.OK);
    }
}