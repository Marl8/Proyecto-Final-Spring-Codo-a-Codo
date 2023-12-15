package agencias.service.controllers;

import agencias.service.models.dto.Request.VueloRequestDTO;
import agencias.service.service.VueloService;
import agencias.service.service.impl.VueloServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vuelo")
@Validated
public class VueloController {

    VueloService vueloService;

    public VueloController(VueloServiceImpl vueloService){
        this.vueloService = vueloService;
    }

    @GetMapping("/listarVuelos")
    public ResponseEntity<?> listarVuelos(){
        return new ResponseEntity<>(vueloService.mostrarVuelos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarVuelo(@Valid @RequestBody VueloRequestDTO vueloRequestDTO){
            return new ResponseEntity<>(vueloService.crearVuelo(vueloRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{idVuelo}")
    public ResponseEntity<?> traerVuelo(
            @PathVariable @Positive(message = "Debe ser un número positivo") Long idVuelo){
        return new ResponseEntity<>(vueloService.vueloPorId(idVuelo), HttpStatus.OK);
    }

    @PutMapping("/{idVuelo}")
    public ResponseEntity<?> modificarVuelo(
            @PathVariable @Positive(message = "Debe ser un número positivo") Long idVuelo,
                                        @Valid @RequestBody VueloRequestDTO vueloRequestDTO){
        return new ResponseEntity<>(vueloService.editarVuelo(idVuelo, vueloRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{idVuelo}")
    public ResponseEntity<?> eliminarVuelo(
            @PathVariable @Positive(message = "Debe ser un número positivo") Long idVuelo){
        return new ResponseEntity<>(vueloService.eliminarVueloPorId(idVuelo), HttpStatus.OK);
    }

}
