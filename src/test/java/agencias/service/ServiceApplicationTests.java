package agencias.service;

import agencias.service.models.dto.Request.AerolineaRequestDTO;
import agencias.service.models.dto.Response.AerolineaResponseDTO;
import agencias.service.service.AerolineaService;
import agencias.service.service.impl.AerolineaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class ServiceApplicationTests {
	@Autowired
	AerolineaService aerolineaService;
	@Test
	void contextLoads() {
	}

	/* probando el TEST que hizo el profe sin mockito
	@Test
	@DisplayName("Test guardar aerolinea por Happy path")
	void guardarAerolineaHappyPath(){
		//Arrange
		AerolineaRequestDTO argumentoSUT =new AerolineaRequestDTO("Latam","12345678912");
		AerolineaResponseDTO expected = new AerolineaResponseDTO(argumentoSUT,"La aerolinea Latam se guardo correctamente");
		//ACT
		AerolineaResponseDTO actual=aerolineaService.guardarAerolinea(argumentoSUT);
		//Assert
		assertEquals(expected,actual);
		*/

	}


