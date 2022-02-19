package com.codemon.authserver.utils;

import org.springframework.http.HttpStatus;

public class Response<T> {
    public int status;
    public String message;
    public T data;

    public Response(T data){
        status = HttpStatus.OK.value();
        this.message = "Ok";
        this.data = data;
    }

    public Response(T data, String message){
        status = HttpStatus.OK.value();
        this.data = data;
        this.message = message;
    }

    public Response(T data, String message, HttpStatus status){
        this.status = status.value();
        this.data = data;
        this.message = message;
    }

    public void test(String nombre){}
    public void test(long id){}
}
