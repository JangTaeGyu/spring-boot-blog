package com.study.blog.account.presentation.response;

import com.study.blog.account.application.query.data.JwtTokenData;
import lombok.Getter;

@Getter
public class JwtTokenResponse {
    private final JwtTokenData data;

    public JwtTokenResponse(JwtTokenData data) {
        this.data = data;
    }
}
