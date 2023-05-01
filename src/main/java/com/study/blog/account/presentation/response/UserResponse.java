package com.study.blog.account.presentation.response;

import com.study.blog.account.domain.UserDto;
import lombok.Getter;

@Getter
public class UserResponse {
    private final UserDto data;

    public UserResponse(UserDto data) {
        this.data = data;
    }
}
