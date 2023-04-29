package com.study.blog.account.application;

import com.study.blog.springboot.exception.HttpException;

public class RegisterException extends HttpException {
    public RegisterException(String message) {
        super(message);
    }
}
