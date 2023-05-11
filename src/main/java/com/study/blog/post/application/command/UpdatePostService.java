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

        // 카테고리가 변경이 되었으면 카테고리 수정
        if (!Objects.equals(post.getCategoryFK().getId(), request.getCategoryId())) {
            Category category = categoryFetcher.fetchCategoryBy(request.getCategoryId());
            post.setCategoryFK(new CategoryFK(category.getId()));
        }

        post.update(request.getTitle(), request.getBody());
        post.generateSlug(slugGenerator);
        return PostMapper.toDto(post);
    }
}
