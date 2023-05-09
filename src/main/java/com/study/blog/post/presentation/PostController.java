package com.study.blog.post.presentation;

import com.study.blog.post.application.command.CreatePostService;
import com.study.blog.post.application.command.request.InputPostRequest;
import com.study.blog.post.domain.PostDto;
import com.study.blog.post.presentation.response.PostResponse;
import com.study.blog.springboot.constant.OpenApiConstant;
import com.study.blog.springboot.exception.ValidationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(
            summary = "포스트 등록",
            security = @SecurityRequirement(name = OpenApiConstant.SECURITY_NAME))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable Entity",
                    content = @Content(schema = @Schema(implementation = ValidationResponse.class)))
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody @Valid InputPostRequest request) {
        log.info("[Create] - {}", request);

        PostDto post = createPostService.createPost(request);
        log.info("[Create] - successful");

        PostResponse response = new PostResponse(post);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
