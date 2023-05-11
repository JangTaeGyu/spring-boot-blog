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

        // slug 중복 체크 로직에서 select 를 실행을 하기 때문에 해당 부분을 post entity 상단에 입력을 해야지 update 구문이 2번 발생 하지 않는다.
        post.generateSlug(slugGenerator, request.getTitle());

        // 카테고리가 변경이 되었으면 카테고리 수정
        if (!Objects.equals(post.getCategoryFK().getId(), request.getCategoryId())) {
            Category category = categoryFetcher.fetchCategoryBy(request.getCategoryId());
            post.setCategoryFK(new CategoryFK(category.getId()));
        }

        post.update(request.getBody());

        return PostMapper.toDto(post);
    }
}
