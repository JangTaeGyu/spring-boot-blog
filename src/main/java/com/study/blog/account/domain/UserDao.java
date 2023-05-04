package com.study.blog.account.domain;

import java.util.Optional;

public interface UserDao {
    void updateLatestAccessByEmail(String email);
    Optional<UserDto> findUserDetailsByCode(String code);
}
