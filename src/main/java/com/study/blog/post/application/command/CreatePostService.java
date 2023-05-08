package com.study.blog.post.application.command;

import com.study.blog.post.application.command.request.InputPostRequest;
import com.study.blog.post.application.query.DuplicateSlug;
import com.study.blog.post.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePostService {
    private final PostRepository postRepository;
    private final SlugGenerator slugGenerator;
    private final DuplicateSlug duplicateSlug;

    private Post makePostEntity(InputPostRequest request) {
        log.info("[MakePostEntity] - start");
        return new Post(request.getTitle(), request.getBody());
    }

    public PostDto createPost(InputPostRequest request) {
        Post post = makePostEntity(request);
        post.generateSlug(slugGenerator);
        duplicateSlug.check(post.getSlug());
        postRepository.save(post);
        return PostMapper.toDto(post);
    }
}
