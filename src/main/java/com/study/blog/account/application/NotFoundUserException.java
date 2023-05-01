package com.study.blog.account.application;

import com.study.blog.springboot.exception.HttpException;

public class NotFoundUserException extends HttpException {
    private static final String MESSAGE = "error.entity.user.notFound";

    public NotFoundUserException() {
        super(MESSAGE);
    }
}
