package com.study.blog.account.application.command.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdateUserRequest {
    @NotBlank
    @Schema(description = "이름")
    private String name;
}
