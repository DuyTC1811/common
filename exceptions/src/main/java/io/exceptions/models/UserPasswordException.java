package io.exceptions.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserPasswordException extends RuntimeException {
    public UserPasswordException(String username, String password) {
        super(String.format("Username [%s]: Password [%s] Unable to void", username, password));
    }
}
