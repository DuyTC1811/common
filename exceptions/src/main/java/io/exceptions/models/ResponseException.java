package io.exceptions.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResponseException extends RuntimeException{
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy HH:MM:SS",
            timezone = "Asia/Ho_Chi_Minh")
    private LocalDateTime timeStamp = LocalDateTime.now();
    private String message;

    public ResponseException(String message) {
        this.message = message;
    }
}
