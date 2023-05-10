package com.study.blog.post.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface PostRepository extends Repository<Post, Long> {
    void save(Post post);
    Optional<Post> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
