package com.example.demo.controller;

import com.example.demo.model.RequestError;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.RequestContext;

import java.util.Map;

@ControllerAdvice
@AllArgsConstructor
public class ControllerExceptionHandler {

    @ResponseStatus()
    @ResponseBody
    @ExceptionHandler(Exception.class)
    RequestError exceptionHandler(Exception e) {
        return new RequestError("[Error MSG] " + e.getMessage() + "[Further Info] " + e.getCause());
    }

}
