package com.study.blog.post.application.command;

import com.study.blog.post.application.NotFoundPostException;
import com.study.blog.post.application.command.request.InputPostRequest;
import com.study.blog.post.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePostService {
    private final PostRepository postRepository;
    private final SlugGenerator slugGenerator;

    @Transactional
    public PostDto updatePost(String slug, InputPostRequest request) {
        Post post = postRepository.findBySlug(slug).orElseThrow(NotFoundPostException::new);
        post.update(request.getTitle(), request.getBody());
        post.generateSlug(slugGenerator);
        return PostMapper.toDto(post);
    }
}
