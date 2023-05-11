package com.study.blog.post.application.command;

import com.study.blog.category.application.query.CategoryFetcher;
import com.study.blog.category.domain.Category;
import com.study.blog.post.application.command.request.InputPostRequest;
import com.study.blog.post.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePostService {
    private final PostRepository postRepository;
    private final CategoryFetcher categoryFetcher;
    private final SlugGenerator slugGenerator;

    private Post makePostEntity(InputPostRequest request) {
        log.info("[MakePostEntity] - start");
        Post post = new Post(request.getTitle(), request.getBody());

        // 카테고리 추가
        Category category = categoryFetcher.fetchCategoryBy(request.getCategoryId());
        post.setCategory(category);
        return post;
    }

    public PostDto createPost(InputPostRequest request) {
        Post post = makePostEntity(request);
        post.generateSlug(slugGenerator);
        postRepository.save(post);
        return PostMapper.toDto(post);
    }
}
