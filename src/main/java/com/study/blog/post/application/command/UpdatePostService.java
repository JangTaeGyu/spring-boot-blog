package com.study.blog.post.application.command;

import com.study.blog.category.application.query.CategoryFetcher;
import com.study.blog.category.domain.Category;
import com.study.blog.post.application.NotFoundPostException;
import com.study.blog.post.application.command.request.InputPostRequest;
import com.study.blog.post.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdatePostService {
    private final PostRepository postRepository;
    private final CategoryFetcher categoryFetcher;
    private final SlugGenerator slugGenerator;

    @Transactional
    public PostDto updatePost(String slug, InputPostRequest request) {
        Post post = postRepository.findBySlug(slug).orElseThrow(NotFoundPostException::new);

        post.update(
                slugGenerator.generate(request.getTitle(), post.getSlug()),
                request.getTitle(),
                request.getBody());

        // 카테고리가 변경이 되었으면 카테고리 수정
        if (!Objects.equals(post.getCategoryFK().getId(), request.getCategoryId())) {
            Category category = categoryFetcher.fetchCategoryBy(request.getCategoryId());
            post.setCategoryFK(new CategoryFK(category.getId()));
        }

        return PostMapper.toDto(post);
    }
}
