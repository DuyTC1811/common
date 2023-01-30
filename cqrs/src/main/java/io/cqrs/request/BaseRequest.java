package io.cqrs.request;
public class BaseRequest {
    private String message;

    public BaseRequest() {
    }

    public BaseRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
