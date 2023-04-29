package com.study.blog.account.domain;

public interface UserDao {
    void updateLatestAccessByEmail(String email);
}
