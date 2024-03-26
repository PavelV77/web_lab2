package com.example.weblab.controllers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> exception(EmptyResultDataAccessException ex){
        return new ResponseEntity<>("ERROR: NOT FOUND", HttpStatus.NOT_FOUND);
    }

}
