package agencias.service.controllers;

import agencias.service.exceptions.CustomException;
import agencias.service.models.dto.Request.ReservaRequestDTO;
import agencias.service.service.ReservaService;
import agencias.service.service.impl.ReservaServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.undo.CannotUndoException;
import java.time.LocalDate;
@RestController
@RequestMapping("/api/reserva")
@Validated
public class ReservaController {
    ReservaService reservaServ;

    public ReservaController(ReservaServiceImpl reservaServ) {
        this.reservaServ = reservaServ;
    }

    //creación de reportes cantidad de reservas entre fechas

    @GetMapping("/reporte")
    public  ResponseEntity<?> generarReporte(
                                             @NotNull(message = "Debe indicarse una fecha") @RequestParam
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate since,
                                             @NotNull(message = "Debe indicarse una fecha") @RequestParam
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to){



        return new ResponseEntity<> (reservaServ.generarReporte(since, to), HttpStatus.OK );
    }

    @GetMapping("/reporte/{fecha}")
    public ResponseEntity<?>reporteParaUnaFecha(@NotNull(message = "Debe indicarse una fecha")
                                                    @PathVariable(name = "fecha")
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha){
        return new ResponseEntity<> ( reservaServ.generarReporteUnaFecha ( fecha ), HttpStatus.OK );
    }

    // Lista de reservas por usuario
    @GetMapping("/user/{id}")
    public ResponseEntity<?> reservasByUser(
            @PathVariable @Positive(message = "Debe ser un número positivo") Long id){
        return new ResponseEntity<>(reservaServ.reservasByUsuario(id), HttpStatus.OK);
    }

    @PostMapping("/alta")
    public ResponseEntity<?> crearReserva(@Valid @RequestBody ReservaRequestDTO reservaRequestDTO){
        try {
            return new ResponseEntity<>(reservaServ.crearReserva(reservaRequestDTO), HttpStatus.CREATED);
        } catch (CustomException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }
}
