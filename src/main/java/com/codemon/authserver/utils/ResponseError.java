package com.codemon.authserver.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Map;

public class ResponseError {
    public Date timestamp;
    public int status;
    public String error;
    public String message;
    public String path;
    public Map errors;

    public ResponseError(HttpStatus httpStatus, String message, String path) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
        timestamp = new Date();
    }

    public ResponseError(HttpStatus httpStatus, String message, String path, Map errors) {
        this.timestamp = new Date();
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
        this.errors = errors;
    }
}
