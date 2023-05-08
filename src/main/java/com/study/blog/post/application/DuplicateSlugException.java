package com.study.blog.post.application;

import com.study.blog.springboot.exception.HttpException;

public class DuplicateSlugException extends HttpException {
    private static final String MESSAGE = "error.component.post.duplicateSlug";

    public DuplicateSlugException() {
        super(MESSAGE);
    }
}
