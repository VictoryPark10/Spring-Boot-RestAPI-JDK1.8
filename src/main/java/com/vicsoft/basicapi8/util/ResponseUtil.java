package com.vicsoft.basicapi8.util;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    private static final DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static Map<String, Object> getErrorBody(HttpStatus httpStatus, String message, String path) {
        Map<String, Object> defaultBody = new HashMap<>();
        defaultBody.put("timestamp", LocalDateTime.now().format(df));
        defaultBody.put("status", httpStatus.value());
        defaultBody.put("error", httpStatus.getReasonPhrase());
        defaultBody.put("path", path);

        Map<String, Object> body = new HashMap<>();
        body.put("message", message);
        body.put("body", defaultBody);

        return body;
    }



}
