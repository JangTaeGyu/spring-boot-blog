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


    public PostDto createPost(InputPostRequest request) {
        // 카테고리 검증
        Category category = categoryFetcher.fetchCategoryBy(request.getCategoryId());
        
        // post entity 생성
        Post post = new Post(new CategoryFK(category.getId()), request.getTitle(), request.getBody());
        
        // slug 생성
        post.generateSlug(slugGenerator);
        
        // post 저장
        postRepository.save(post);
        return PostMapper.toDto(post);
    }
}
