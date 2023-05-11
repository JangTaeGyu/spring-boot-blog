package com.study.blog.post.application.command.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @ToString
public class InputPostRequest {
    @NotNull
    private Long categoryId;

    @NotBlank
    private String title;

    @NotNull
    private String body;
}
