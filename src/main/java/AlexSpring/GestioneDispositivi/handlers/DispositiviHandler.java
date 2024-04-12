package AlexSpring.GestioneDispositivi.handlers;

import AlexSpring.GestioneDispositivi.exceptions.BadRequestException;
import AlexSpring.GestioneDispositivi.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class DispositiviHandler {


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleBadRequest(BadRequestException ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }
//
//    @ExceptionHandler(NotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//
//    public ErrorsPayload handleNotFound(NotFoundException ex){
//        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
//    public ErrorsPayload handleGenericErrors(Exception ex){
//        ex.printStackTrace();
//        return new ErrorsPayload("Problema lato server", LocalDateTime.now());
//    }
}
