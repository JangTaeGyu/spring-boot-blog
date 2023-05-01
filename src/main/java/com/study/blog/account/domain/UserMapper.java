package com.study.blog.account.domain;

public class UserMapper {
    public static UserDto toDto(User user) {
        return new UserDto(
                user.getCode(),
                user.getEmail(),
                user.getName(),
                user.getImagePublicUrl(),
                user.getLatestAccessedAt(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getRoles());
    }
}
