package com.study.blog.category.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CategoryRepository extends Repository<Category, Long> {
    Optional<Category> findById(Long id);
}
