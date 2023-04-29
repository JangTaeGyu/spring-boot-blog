package com.study.blog.account.application;

import com.study.blog.springboot.exception.HttpException;

public class WithdrawalUserException extends HttpException {
    private static final String MESSAGE = "error.service.login.withdrawalUser";

    public WithdrawalUserException() {
        super(MESSAGE);
    }
}
