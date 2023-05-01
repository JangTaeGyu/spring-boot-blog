package com.study.blog.account.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long>, UserDao {
    Optional<User> findByEmail(String email);
    Optional<User> findByCode(String code);
    boolean existsByCode(String code);
    boolean existsByEmail(String email);
    void save(User user);
}
