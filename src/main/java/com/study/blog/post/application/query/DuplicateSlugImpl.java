package com.study.blog.post.application.query;

import com.study.blog.post.application.DuplicateSlugException;
import com.study.blog.post.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DuplicateSlugImpl implements DuplicateSlug {
    private final PostRepository postRepository;

    @Override
    public void check(String slug) {
        if (postRepository.existsBySlug(slug)) throw new DuplicateSlugException();
        log.info("[check] - slug checked");
    }
}
