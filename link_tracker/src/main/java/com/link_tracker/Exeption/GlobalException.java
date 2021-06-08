package com.link_tracker.Exeption;
import com.link_tracker.DTO.GlobalResponseException;
import com.link_tracker.Time.Time;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.IOException;

@ControllerAdvice
public class GlobalException {

    private GlobalResponseException globalResponseException;

    //Creamos nuestras Propias exepciones, y dependiendo de cual de todas se dispare, lo mostramos mediante el DTO globalResponseException.

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseException> exception(Exception e){
        globalResponseException = new GlobalResponseException("[Exception] - " +
                e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), Time.getTime());
        return new ResponseEntity<>(globalResponseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<GlobalResponseException> Ioexception(IOException e) {
        globalResponseException = new GlobalResponseException("[IOException] - " +
                e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), Time.getTime());
        return new ResponseEntity<>(globalResponseException,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GlobalResponseException> runetimeException(RuntimeException e){
        globalResponseException = new GlobalResponseException("[RunetimeException] - " +
                e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), Time.getTime());
        return new ResponseEntity<>(globalResponseException,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<GlobalResponseException> nullPointerException(NullPointerException e){
        globalResponseException = new GlobalResponseException("[NullPointerException] - " +
                e.getMessage(), HttpStatus.NOT_FOUND.value(), Time.getTime());
        return new ResponseEntity<>(globalResponseException,HttpStatus.NOT_FOUND);
    }

}
