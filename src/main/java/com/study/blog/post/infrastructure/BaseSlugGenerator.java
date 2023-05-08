package com.study.blog.post.infrastructure;

import com.study.blog.post.domain.SlugGenerator;
import org.springframework.stereotype.Component;

@Component
public class BaseSlugGenerator implements SlugGenerator {
    private String convertToSlug(String title) {
        return title.replaceAll("[^a-zA-Z0-9가-힣\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
    }

    @Override
    public String generate(String title) {
        return convertToSlug(title);
    }
}
