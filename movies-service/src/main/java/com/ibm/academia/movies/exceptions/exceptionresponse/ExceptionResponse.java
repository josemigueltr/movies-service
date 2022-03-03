package com.ibm.academia.movies.exceptions.exceptionresponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ExceptionResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private Integer status;
    private HttpStatus error;
    private String message;
    private String path;

    public ExceptionResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ExceptionResponse(LocalDateTime timestamp, Integer status, HttpStatus error, String message, String path) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    @Override
    public String toString() {
        return "ExceptionResponse [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message="
                + message + ", path=" + path + "]";
    }
}
