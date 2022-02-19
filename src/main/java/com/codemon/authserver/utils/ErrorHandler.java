package com.codemon.authserver.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    ResponseEntity<ResponseError> handleAnyException(Exception e, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<ResponseError> handleResponseException(ResponseStatusException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ResponseError(e.getStatus(), e.getReason(), request.getRequestURI()), e.getStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseError> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request){
        return new ResponseEntity<>(
                new ResponseError(HttpStatus.FORBIDDEN, e.getMessage(), request.getRequestURI()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ResponseError> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getFieldErrors().forEach(error -> {
            if (!errors.containsKey(error.getField()))
                errors.put(error.getField(), new ArrayList<>());
            errors.get(error.getField()).add(error.getDefaultMessage());
        });
        return new ResponseEntity<>(new ResponseError(
                HttpStatus.BAD_REQUEST,
                "Parametros no validos",
                request.getRequestURI(),
                errors),
                HttpStatus.BAD_REQUEST);
    }
}
