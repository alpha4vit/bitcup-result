package com.testapp.municipalitytax.web.controller;


import com.sun.net.httpserver.HttpsServer;
import com.testapp.municipalitytax.web.exception.ExceptionBody;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFound(final RuntimeException ex){
        Map<String, String> errors = new HashMap<>();
        ExceptionBody exceptionBody = new ExceptionBody(ex.getMessage());
        exceptionBody.setErrors(errors);
        return exceptionBody;
    }


}