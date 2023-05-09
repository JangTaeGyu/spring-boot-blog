package com.study.blog.post.domain;

import org.springframework.data.repository.Repository;

public interface PostRepository extends Repository<Post, Long> {
    void save(Post post);

    boolean existsBySlug(String slug);
}