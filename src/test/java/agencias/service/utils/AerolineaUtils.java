package agencias.service.utils;

import agencias.service.models.dto.Request.AerolineaRequestDTO;
import agencias.service.models.entity.Aerolinea;

import java.util.List;

public class AerolineaUtils {

    public static AerolineaRequestDTO aereoDto1(){
        return new AerolineaRequestDTO("Latam SA", "30251698724");
    }

    public static AerolineaRequestDTO aereoDto2(){
        return new AerolineaRequestDTO("Avianca SA", "30195691253");
    }

    public static Aerolinea aereo1(){
        Aerolinea aereo = new Aerolinea();
        aereo.setIdAerolinea(1L);
        aereo.setRazonSocial("Latam SA");
        aereo.setCuit("30251698724");
        return aereo;
    }

    public static Aerolinea aereo2(){
        Aerolinea aereo = new Aerolinea();
        aereo.setRazonSocial("Avianca SA");
        aereo.setCuit("30195691253");
        return aereo;
    }

    public static AerolineaRequestDTO aereoDto3(){
        AerolineaRequestDTO aereo = new AerolineaRequestDTO();
        aereo.setRazonSocial("Latam SA");
        return aereo;
    }

    public static AerolineaRequestDTO aereoModificadoDto1(){
        return new AerolineaRequestDTO("Iberia SA", "30295024586");
    }

    public static Aerolinea aereoModificado1(){
        Aerolinea aereo = new Aerolinea();
        aereo.setIdAerolinea(1L);
        aereo.setRazonSocial("Iberia SA");
        aereo.setCuit("30295024586");
        return aereo;
    }

    public static List<AerolineaRequestDTO> ListaAerolineasDto(){
        return List.of(aereoDto1(),aereoDto2());
    }

    public static List<Aerolinea> ListaAerolineas(){
        return List.of(aereo1(),aereo2());
    }
}
