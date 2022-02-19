package com.codemon.authserver.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        final ResponseError responseError = new ResponseError(HttpStatus.FORBIDDEN, accessDeniedException.getMessage(), request.getRequestURI());
        final String json = new ObjectMapper().writeValueAsString(responseError);
        response.getWriter().write(json);
        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
    }
}
