package com.study.blog.account.application;

import com.study.blog.springboot.exception.HttpException;

public class NotFoundRoleException extends HttpException {
    private static final String MESSAGE = "error.entity.role.notFound";

    public NotFoundRoleException() {
        super(MESSAGE);
    }
}
