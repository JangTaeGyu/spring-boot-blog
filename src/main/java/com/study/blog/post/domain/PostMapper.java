package com.study.blog.post.domain;

public class PostMapper {
    public static PostDto toDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getSlug(),
                post.getTitle(),
                post.getBody(),
                post.getShow(),
                post.getCreatedAt(),
                post.getUpdatedAt());
    }
}
