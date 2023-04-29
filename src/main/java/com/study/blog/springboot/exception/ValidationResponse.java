package com.study.blog.springboot.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ValidationResponse extends ErrorResponse {
    private final Map<String, String> errors;

    public ValidationResponse(Integer status, String path, String message, Map<String, String> errors) {
        super(status, path, message);
        this.errors = errors;
    }
}
