package com.study.blog.account.infrastructure.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.blog.springboot.exception.ErrorResponse;
import com.study.blog.springboot.util.MessagesUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ErrorResponse eResponse = new ErrorResponse(httpStatus.value(), request.getRequestURI(), MessagesUtils.by("error.authenticationRequired"));

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(eResponse.getStatus());
        response.getWriter().println(objectMapper.writeValueAsString(eResponse));
    }
}
