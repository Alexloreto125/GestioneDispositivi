package AlexSpring.GestioneDispositivi.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id){
        super("Nessun record trovato con id: "+ id
        );
    }


    public NotFoundException(String message){
        super(message);
    }
}
