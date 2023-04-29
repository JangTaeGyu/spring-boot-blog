package com.study.blog.account.application.query.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class JwtTokenData {
    @Schema(description = "토큰 타입")
    private final String tokenType = "Bearer";

    @Schema(description = "토큰 Value")
    private final String accessToken;

    @Schema(description = "토큰 만료 시간")
    private final Long expireTime;

    public JwtTokenData(String accessToken, Long expireTime) {
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }
}
