package com.study.blog.category.application;

import com.study.blog.springboot.exception.HttpException;

public class NotFoundCategoryException extends HttpException {
    private static final String MESSAGE = "error.entity.category.notFound";

    public NotFoundCategoryException() {
        super(MESSAGE);
    }
}
