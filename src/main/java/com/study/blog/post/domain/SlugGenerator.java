package com.study.blog.post.domain;

public interface SlugGenerator {
    String generate(String title, String currentSlug);
}
