package com.vicsoft.basicapi8.exception.handler;

import com.vicsoft.basicapi8.exception.EmptyBodyException;
import com.vicsoft.basicapi8.exception.UserNotFoundException;
import com.vicsoft.basicapi8.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> userNotFound(UserNotFoundException e) {
        log.warn(e.getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ResponseUtil.getErrorBody(e.getHttpStatus(), e.getMessage(), e.getPath()));
    }

    @ExceptionHandler(EmptyBodyException.class)
    public ResponseEntity<Map<String, Object>> emptyBody(EmptyBodyException e) {
        log.warn(e.getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ResponseUtil.getErrorBody(e.getHttpStatus(), e.getMessage(), e.getPath()));
    }

}
