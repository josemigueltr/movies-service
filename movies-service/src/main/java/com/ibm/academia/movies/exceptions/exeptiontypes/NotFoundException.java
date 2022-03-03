package com.ibm.academia.movies.exceptions.exeptiontypes;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ExceptionType {
    public NotFoundException(HttpStatus status, String message) {
        super(status,message);
    }
}
