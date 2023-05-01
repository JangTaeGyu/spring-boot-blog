package com.study.blog.springboot.exception;

import com.study.blog.springboot.util.MessagesUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationResponse response = new ValidationResponse(
                httpStatus.value(),
                request.getDescription(false).replace("uri=", ""),
                MessagesUtils.by("error.validation"),
                errors
        );

        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(HttpException e, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(e.getHttpStatus().value(), request.getRequestURI(), e.getMessage());
        return new ResponseEntity<>(response, e.getHttpStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = MessagesUtils.by("error.internalServer");
        ErrorResponse response = new ErrorResponse(httpStatus.value(), request.getRequestURI(), message, e.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }
}
