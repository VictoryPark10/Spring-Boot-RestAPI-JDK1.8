package com.vicsoft.basicapi8.controller;

import com.vicsoft.basicapi8.service.UserService;
import com.vicsoft.basicapi8.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/basic/api")
public class BasicController {

    private final UserService userService;

    public BasicController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/v1/users/{id}", method = { RequestMethod.GET }, produces = "application/json; charset=utf8")
    public ResponseEntity<Map<String, Object>> getUserInfo(HttpServletRequest httpServletRequest, RequestEntity<Map<String, Object>> request, @PathVariable String id) {
        try {
            return userInfo(httpServletRequest, request, id);
        } catch (Exception e) {
            log.warn("Exception occurred while Processing API: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(ResponseUtil.getErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() ,httpServletRequest.getServletPath()));
        }
    }

    private ResponseEntity<Map<String, Object>> userInfo(HttpServletRequest httpServletRequest, RequestEntity<Map<String, Object>> request, String id) throws Exception {
        if (!MediaType.APPLICATION_JSON.includes(request.getHeaders().getContentType())) {
            log.error("Content-type is not included 'application/json'");

            return ResponseEntity
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body(ResponseUtil.getErrorBody(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Content-type have to include 'application/json'", httpServletRequest.getServletPath()));
        }
        if (!HttpMethod.GET.equals(request.getMethod())) {
            log.error("Http Request Method is not 'GET'");

            return ResponseEntity
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body(ResponseUtil.getErrorBody(HttpStatus.METHOD_NOT_ALLOWED, httpServletRequest.getServletPath() + " Request's HTTP Method support only 'GET' Method", httpServletRequest.getServletPath()));
        }

        return executeUserInfo(httpServletRequest, id);
    }

    private ResponseEntity<Map<String, Object>> executeUserInfo(HttpServletRequest httpServletRequest, String id) throws Exception {
        return ResponseEntity.ok(userService.userInfo(httpServletRequest, id));
    }

}
