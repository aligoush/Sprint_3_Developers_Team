package exceptions;
import model.entities.Player;


public class CertificateAlreadyExistsException extends Exception{

    public CertificateAlreadyExistsException(String message){
        super(message);
    }
}


