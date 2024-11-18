package exceptions;

public class NoAvailablePlayersException extends Exception{
    public NoAvailablePlayersException(String message){
        super(message);
    }
}
