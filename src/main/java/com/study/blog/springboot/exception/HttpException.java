package com.study.blog.springboot.exception;

import com.study.blog.springboot.util.MessagesUtils;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {
    private final HttpStatus httpStatus;

    public HttpException(String message) {
        super(MessagesUtils.by(message));
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public HttpException(String message, HttpStatus httpStatus) {
        super(MessagesUtils.by(message));
        this.httpStatus = httpStatus;
    }
}
