package com.study.blog.post.application.command;

import com.study.blog.category.application.query.CategoryFetcher;
import com.study.blog.post.application.command.request.InputPostRequest;
import com.study.blog.post.domain.PostDto;
import com.study.blog.post.domain.PostRepository;
import com.study.blog.post.domain.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePostService {
    private final PostRepository postRepository;
    private final CategoryFetcher categoryFetcher;
    private final SlugGenerator slugGenerator;

    @Transactional
    public PostDto updatePost(String slug, InputPostRequest request) {
//        Post post = postRepository.findBySlug(slug).orElseThrow(NotFoundPostException::new);
//        Category category = post.getCategory();
//        System.out.println(category.getName());
////        Category category = categoryFetcher.fetchCategoryBy(request.getCategoryId());
//        post.update(request.getTitle(), request.getBody());
////        post.setCategory(category);
//        post.generateSlug(slugGenerator);
//        return PostMapper.toDto(post);
        return null;
    }
}
