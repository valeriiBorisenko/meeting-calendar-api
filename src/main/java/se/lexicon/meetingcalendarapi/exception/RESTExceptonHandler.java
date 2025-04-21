package se.lexicon.meetingcalendarapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RESTExceptonHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            DataNotFoundException.class,
            IllegalArgumentException.class,
            NullPointerException.class,
    })
    public ResponseEntity<ErrorDTO> handleCustomException(Exception ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO responseBody = new ErrorDTO(status, ex.getMessage());
        return ResponseEntity.status(status).body(responseBody);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder details = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> {
                    details.append(error.getField());
                    details.append(": ");
                    details.append(error.getDefaultMessage());
                    details.append(", ");
                }
        );
        ErrorDTO responseBody = new ErrorDTO(status, details.toString(), request.getDescription(false));

        return ResponseEntity.badRequest().body(responseBody);
    }
}
