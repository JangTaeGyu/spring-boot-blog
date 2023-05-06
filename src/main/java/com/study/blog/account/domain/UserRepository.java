package com.study.blog.account.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long>, UserDao {
    Optional<User> findByEmail(String email);

    @Query(
            nativeQuery = true,
            value = "select * from users where code = :code and deleted_at is null")
    Optional<User> findByCode(String code);
    boolean existsByCode(String code);
    boolean existsByEmail(String email);
    void save(User user);
}
