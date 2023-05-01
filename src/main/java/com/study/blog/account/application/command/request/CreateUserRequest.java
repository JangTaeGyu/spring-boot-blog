package com.study.blog.account.application.command.request;

import com.study.blog.account.domain.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter @ToString
public class CreateUserRequest extends RegisterRequest {
    @NotNull
    @Schema(description = "룰(역활) 타입")
    private RoleType roleType;
}
