package io.exceptions.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private String message;

    public BaseException(String message) {
        this.message = message;
    }
}
