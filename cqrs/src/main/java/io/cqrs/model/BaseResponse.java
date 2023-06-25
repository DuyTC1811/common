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
        this.code = (data == null) ? 400 : 201;
        this.message = (data == null) ? "Bad Request" : "Success!";
        this.success = (data != null);
        return data;
    }
}
