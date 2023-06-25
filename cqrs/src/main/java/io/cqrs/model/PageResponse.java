package io.cqrs.model;

import io.cqrs.enums.CodeError;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {
    private int code;
    private boolean success;
    private String message;
    private int limit;
    private int offset;
    private int totalItems;
    private List<T> data;

    public PageResponse(List<T> data) {
        this.data = data;
    }

    public PageResponse(List<T> data, int limit, int offset, int totalItems) {
        this.code = (data == null) ? 400 : 201;
        this.message = (data == null) ? "Bad Request" : "Success!";
        this.success = (data != null);
        this.limit = limit;
        this.offset = offset;
        this.totalItems = totalItems;
        this.data = data;
    }
    public PageResponse(List<T> data, int limit, int offset, CodeError code) {
        this.code = code.getCode();
        this.message = code.getName();
        this.success = true;
        this.limit = limit;
        this.offset = offset;
        this.data = data;
    }
}
