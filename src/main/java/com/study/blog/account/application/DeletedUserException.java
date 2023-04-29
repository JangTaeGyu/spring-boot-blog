package com.study.blog.account.application;

import com.study.blog.springboot.exception.HttpException;

public class DeletedUserException extends HttpException {
    private static final String MESSAGE = "error.service.login.deletedUser";

    public DeletedUserException() {
        super(MESSAGE);
    }
}
