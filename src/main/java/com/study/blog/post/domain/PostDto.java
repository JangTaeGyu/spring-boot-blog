package com.study.blog.post.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    @JsonIgnore
    private final Long id;
    private final String slug;
    private final String title;
    private final String body;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    @JsonIgnore
    private Boolean show;

    public PostDto(Long id,
                   String slug,
                   String title,
                   String body,
                   Boolean show,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.body = body;
        this.show = show;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
