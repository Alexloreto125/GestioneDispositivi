package AlexSpring.GestioneDispositivi.exceptions;

import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class BadRequestException extends RuntimeException {
    List<ObjectError> errorList;

    public BadRequestException(String message){
        super(message);

    }

    public BadRequestException(List<ObjectError> errorList){
        super("Errori nel payload");
        this.errorList= errorList;
        System.out.println(errorList);
    }

    public List<ObjectError> getErrorList() {
        return errorList;
    }



}
