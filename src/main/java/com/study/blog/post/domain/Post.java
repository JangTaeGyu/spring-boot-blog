package com.study.blog.post.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @EqualsAndHashCode(of = "id")
@Entity
@Table(name = "posts")
@DynamicInsert @DynamicUpdate
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    @Comment("제목")
    private String title;

    @Column(columnDefinition="TEXT")
    @Comment("본문")
    private String body;

    @ColumnDefault("false")
    @Comment("노출 여부")
    private Boolean show = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Setter
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "category_id"))
    private CategoryFK categoryFK;

    protected Post() {}

    public Post(String slug, String title, String body, CategoryFK categoryFK) {
        this.slug = slug;
        this.title = title;
        this.body = body;
        this.categoryFK = categoryFK;
    }

    public void update(String slug, String title, String body) {
        this.slug = slug;
        this.title = title;
        this.body = body;
    }
}
