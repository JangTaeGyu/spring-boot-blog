package com.study.blog.post.infrastructure;

import com.study.blog.post.application.DuplicateSlugException;
import com.study.blog.post.domain.PostRepository;
import com.study.blog.post.domain.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseSlugGenerator implements SlugGenerator {
    private final PostRepository postRepository;

    private String convertToSlug(String title) {
        return title.replaceAll("[^a-zA-Z0-9가-힣\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
    }

    @Override
    public void checkSlugDuplication(String slug) {
        if (postRepository.existsBySlug(slug)) {
            throw new DuplicateSlugException();
        }
    }

    @Override
    public String generate(String title) {
        return convertToSlug(title);
    }
}
