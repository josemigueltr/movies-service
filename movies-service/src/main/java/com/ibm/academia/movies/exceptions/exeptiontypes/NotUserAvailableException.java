package com.ibm.academia.movies.exceptions.exeptiontypes;

import org.springframework.http.HttpStatus;

public class NotUserAvailableException extends ExceptionType{

    public NotUserAvailableException(HttpStatus status, String message) {
        super(status, message);
    }
}
