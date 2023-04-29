package com.study.blog.account.infrastructure.security.service;

import com.study.blog.account.domain.Role;
import com.study.blog.account.domain.User;

import java.util.Set;

public interface CustomUserDetails {
    User getUser();
    Set<Role> getRoles();
}
