package com.study.blog.account.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter @EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
@DynamicInsert @DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String email;

    @Comment("암호화된 비밀번호")
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

    @Comment("프로필 이미지 업로드 경로")
    private String imageFullFilePath;

    @Comment("프로필 이미지 Url")
    private String imagePublicUrl;

    @Comment("마지막 로그인 시간")
    private LocalDateTime latestAccessedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Comment("탈퇴 시간")
    private LocalDateTime withdrawalAt;

    @Comment("삭제 시간")
    private LocalDateTime deletedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();
}
