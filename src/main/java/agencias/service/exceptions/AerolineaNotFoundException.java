package agencias.service.exceptions;

public class AerolineaNotFoundException extends RuntimeException{

    public AerolineaNotFoundException(String message){
        super(message);
    }
}
