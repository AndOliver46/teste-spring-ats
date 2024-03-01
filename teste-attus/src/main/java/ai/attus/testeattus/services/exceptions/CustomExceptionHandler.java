package ai.attus.testeattus.services.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(PessoaNotFoundException.class)
    public ResponseEntity<CustomError> pessoaNotFoundException(PessoaNotFoundException ex, HttpServletRequest request){
        CustomError customException = new CustomError();
        customException.setTimestamp(LocalDateTime.now());
        customException.setStatus(HttpStatus.NOT_FOUND);
        customException.setPath(request.getRequestURI());
        customException.setError(ex.getMessage());

        return ResponseEntity.status(customException.getStatus()).body(customException);
    }

    @ExceptionHandler(EnderecoNotFoundException.class)
    public ResponseEntity<CustomError> pessoaNotFoundException(EnderecoNotFoundException ex, HttpServletRequest request){
        CustomError customException = new CustomError();
        customException.setTimestamp(LocalDateTime.now());
        customException.setStatus(HttpStatus.NOT_FOUND);
        customException.setPath(request.getRequestURI());
        customException.setError(ex.getMessage());

        return ResponseEntity.status(customException.getStatus()).body(customException);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomError> pessoaNotFoundException(Exception ex, HttpServletRequest request){
        CustomError customException = new CustomError();
        customException.setTimestamp(LocalDateTime.now());
        customException.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        customException.setPath(request.getRequestURI());
        customException.setError(ex.getMessage());

        return ResponseEntity.status(customException.getStatus()).body(customException);
    }

}
