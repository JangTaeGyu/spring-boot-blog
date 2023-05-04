package com.study.blog.account.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class UserDto {
    @Schema(description = "회원 코드")
    private final String code;
    
    @Schema(description = "이메일")
    private final String email;

    @Schema(description = "이름")
    private final String name;

    @Schema(description = "프로필 이미지 Url")
    private final String imagePublicUrl;

    @Schema(description = "마지막 로그인 시간")
    private final LocalDateTime latestAccessedAt;

    @Schema(description = "등록 일자", format = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @Schema(description = "수정 일자", format = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    private final RoleDto role;

    public UserDto(String code,
                   String email,
                   String name,
                   String imagePublicUrl,
                   LocalDateTime latestAccessedAt,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt,
                   Set<Role> roles) {
        this.code = code;
        this.email = email;
        this.name = name;
        this.imagePublicUrl = imagePublicUrl;
        this.latestAccessedAt = latestAccessedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = new RoleDto(roles);
    }

    public UserDto(String code,
                   String email,
                   String name,
                   String imagePublicUrl,
                   LocalDateTime latestAccessedAt,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt,
                   String roleName) {
        this.code = code;
        this.email = email;
        this.name = name;
        this.imagePublicUrl = imagePublicUrl;
        this.latestAccessedAt = latestAccessedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = new RoleDto(roleName);
    }

    @Getter
    private static class RoleDto {
        private final String name;

        public RoleDto(Set<Role> roles) {
            this.name = roles.stream().findFirst().map(Role::getName).orElse(null);
        }

        public RoleDto(String name) {
            this.name = name;
        }
    }
}
