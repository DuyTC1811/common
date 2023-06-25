package io.exceptions.handlers;

import io.exceptions.models.ResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> handlerException(Exception exception) {
        ResponseException response = new ResponseException();
        response.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
