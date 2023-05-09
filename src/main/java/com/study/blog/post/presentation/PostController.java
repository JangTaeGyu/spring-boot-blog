package com.study.blog.post.presentation;

import com.study.blog.post.application.command.CreatePostService;
import com.study.blog.post.application.command.request.InputPostRequest;
import com.study.blog.post.domain.PostDto;
import com.study.blog.post.presentation.response.PostResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "포스트")
@Slf4j
@RestController
@RequestMapping("/api/admin/posts")
@RequiredArgsConstructor
public class PostController {
    private final CreatePostService createPostService;

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody @Valid InputPostRequest request) {
        log.info("[Create] - {}", request);

        PostDto post = createPostService.createPost(request);
        log.info("[Create] - successful");

        PostResponse response = new PostResponse(post);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
