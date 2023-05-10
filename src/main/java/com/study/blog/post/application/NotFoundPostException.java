package com.study.blog.post.application;

import com.study.blog.springboot.exception.HttpException;

public class NotFoundPostException extends HttpException {
    private static final String MESSAGE = "error.entity.post.notFound";

    public NotFoundPostException() {
        super(MESSAGE);
    }
}
