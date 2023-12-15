package agencias.service.exceptionTest;

import agencias.service.exceptions.*;
import agencias.service.models.dto.Response.ErrorDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExceptionTest {

    @Autowired
    ExceptionController controller;

    @Test
    @DisplayName("Test de la excepción personalizada AerolineaNotFoundException")
    void aerolineaException(){
        AerolineaNotFoundException argumentSut = new AerolineaNotFoundException("Excepción lanzada");
        ErrorDTO error = new ErrorDTO(404, "Excepción lanzada");
        ResponseEntity<?> expected = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        ResponseEntity<?> actual = controller.aerolineaNotFound(argumentSut);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test de la excepción personalizada VueloGenericException")
    void vueloException(){
        VueloGenericException argumentSut = new VueloGenericException("Excepción lanzada");
        ErrorDTO error = new ErrorDTO(404, "Excepción lanzada");
        ResponseEntity<?> expected = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        ResponseEntity<?> actual = controller.vueloGenericException(argumentSut);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test de la excepción personalizada TicketGenericException")
    void ticketException(){
        TicketGenericException argumentSut = new TicketGenericException("Excepción lanzada");
        ErrorDTO error = new ErrorDTO(404, "Excepción lanzada");
        ResponseEntity<?> expected = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        ResponseEntity<?> actual = controller.ticketGenericException(argumentSut);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test de la excepción personalizada RolGenericException")
    void rolException(){
        RolGenericException argumentSut = new RolGenericException("Excepción lanzada");
        ErrorDTO error = new ErrorDTO(404, "Excepción lanzada");
        ResponseEntity<?> expected = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        ResponseEntity<?> actual = controller.rolGenericException(argumentSut);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test de la excepción personalizada CustomException")
    void customException(){
        CustomException argumentSut = new CustomException(HttpStatus.NOT_FOUND, "Excepción lanzada");
        ErrorDTO error = new ErrorDTO(404, "Excepción lanzada");
        ResponseEntity<?> expected = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        ResponseEntity<?> actual = controller.customException(argumentSut);

        assertEquals(expected, actual);
    }
}
