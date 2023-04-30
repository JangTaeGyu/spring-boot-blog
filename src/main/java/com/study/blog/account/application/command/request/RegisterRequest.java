package com.study.blog.account.application.command.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class RegisterRequest {
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
}
