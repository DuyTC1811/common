package io.exceptions.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserPasswordException extends RuntimeException {
    public UserPasswordException(String username) {
        super(String.format("Authentication failed for username [%s]", username));
    }
}
