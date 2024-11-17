package com.sap.refactoring.controller;

import com.sap.refactoring.exception.InvalidUserException;
import com.sap.refactoring.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException() {
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<?> handleInvalidUserException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
