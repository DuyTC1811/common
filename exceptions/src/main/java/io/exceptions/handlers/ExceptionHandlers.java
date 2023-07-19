package io.exceptions.handlers;

import io.exceptions.models.ResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> handlerException(Exception exception) {
        LOGGER.error("Info Exception {}", exception.getMessage());
        return ResponseEntity.badRequest().body(new ResponseException(exception.getMessage()));
    }
}
