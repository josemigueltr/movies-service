package com.ibm.academia.movies.exceptions.exeptiontypes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionType extends RuntimeException {
    private HttpStatus status;

    public ExceptionType(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}