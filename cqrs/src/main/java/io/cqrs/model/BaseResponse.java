package io.cqrs.model;

import io.cqrs.enums.CodeError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    private int code;
    private boolean success;
    private String message;
    private T data;

    public BaseResponse(T data) {
        this.data = setData(data);
    }

    public BaseResponse(T data, CodeError code) {
        this.code = code.getCode();
        this.message = code.getName();
        this.success = true;
        this.data = setData(data);
    }

    private T setData(T data) {
        if (data == null) {
            this.code = 400;
            this.message = "Bad Request";
            this.success = false;
        }
        return data;
    }
}
