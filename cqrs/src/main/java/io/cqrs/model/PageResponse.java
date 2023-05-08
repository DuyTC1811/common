package io.cqrs.model;

import io.cqrs.enums.CodeError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponse<T> {
    private int code;
    private boolean success;
    private String message;
    private int limit;
    private int offset;
    private int totalPage;
    private T data;

    public PageResponse(T data) {
        this.data = setData(data);
    }

    public PageResponse(T data, CodeError code) {
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
