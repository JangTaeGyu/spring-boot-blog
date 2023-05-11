package com.study.blog.post.domain;

public interface SlugGenerator {
    void checkSlugDuplication(String slug);
    String generate(String title);
}
