package com.vicsoft.basicapi8.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotNullColumnPutNullException extends Exception {

    private final HttpStatus httpStatus;

    private final String message;

    private final String path;

    public NotNullColumnPutNullException(HttpStatus h, String s, String p) {
        this.httpStatus = h;
        this.message = s;
        this.path = p;
    }

}
