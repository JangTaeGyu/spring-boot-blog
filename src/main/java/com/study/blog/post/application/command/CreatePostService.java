package com.study.blog.post.application.command;

import com.study.blog.post.application.command.request.InputPostRequest;
import com.study.blog.post.domain.Post;
import com.study.blog.post.domain.PostDto;
import com.study.blog.post.domain.PostMapper;
import com.study.blog.post.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePostService {
    private final PostRepository postRepository;

    private Post makePostEntity(InputPostRequest request) {
        log.info("[MakePostEntity] - start");
        return new Post(request.getTitle(), request.getBody());
    }

    public PostDto createPost(InputPostRequest request) {
        Post post = makePostEntity(request);
        postRepository.save(post);
        return PostMapper.toDto(post);
    }
}
