package com.study.blog.account.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RoleRepository extends Repository<Role, Long> {
    Optional<Role> findByName(String name);
}
