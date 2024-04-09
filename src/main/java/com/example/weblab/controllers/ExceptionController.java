package com.example.weblab.controllers;

import com.example.weblab.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> exception(EmptyResultDataAccessException ex){
        return new ResponseEntity<>("ERROR: NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> exceptionNotFound(NotFoundException exception){
        return new ResponseEntity<>("ERROR: NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<?> noSuchElement(NoSuchElementException exception){
        return new ResponseEntity<>("ERROR: NOT FOUND", HttpStatus.NOT_FOUND);
    }

}
