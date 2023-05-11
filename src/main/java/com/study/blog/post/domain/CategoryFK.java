package com.study.blog.post.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable @EqualsAndHashCode(of = "id")
public class CategoryFK implements Serializable {
    @Column(name = "category_id")
    private Long id;

    protected CategoryFK() {}

    public CategoryFK(Long id) {
        this.id = id;
    }
}
