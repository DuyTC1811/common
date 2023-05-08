package io.cqrs.enums;

import lombok.Getter;

@Getter
public enum CodeError {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request");
    private final int code;
    private final String name;

    CodeError(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
