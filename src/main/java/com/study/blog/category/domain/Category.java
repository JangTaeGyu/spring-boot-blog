package com.study.blog.category.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @EqualsAndHashCode(of = "id")
@Entity
@Table(name = "categories")
@DynamicInsert @DynamicUpdate
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    @Comment("카테고리 명")
    private String name;

    @Column(columnDefinition="TEXT")
    @Comment("카테고리 설명")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
