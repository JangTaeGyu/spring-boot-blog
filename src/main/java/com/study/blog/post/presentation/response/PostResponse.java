package com.study.blog.post.presentation.response;

import com.study.blog.post.domain.PostDto;
import lombok.Getter;

@Getter
public class PostResponse {
    private final PostDto data;

    public PostResponse(PostDto data) {
        this.data = data;
    }
}
