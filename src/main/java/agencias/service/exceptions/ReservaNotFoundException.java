package agencias.service.exceptions;

public class ReservaNotFoundException extends RuntimeException{
    public ReservaNotFoundException(String menssage){
        super(menssage);
    }

}
