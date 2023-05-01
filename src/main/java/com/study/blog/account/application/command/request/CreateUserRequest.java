package com.study.blog.account.application.command.request;

import com.study.blog.account.domain.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @ToString
public class CreateUserRequest {
    @NotBlank
    @Email
    @Schema(description = "이메일")
    private String email;

    @NotBlank
    @Schema(description = "이름")
    private String name;

    @NotBlank
    @Schema(description = "비밀번호")
    private String password;

    @NotBlank
    @Schema(description = "비밀번호 확인")
    private String passwordConfirm;

    private RoleType roleType;
}
