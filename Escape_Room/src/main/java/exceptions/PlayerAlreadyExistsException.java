package exceptions;

import model.entities.Player;

public class PlayerAlreadyExistsException extends Exception{
    public PlayerAlreadyExistsException(String message){
        super(message);
    }
}
