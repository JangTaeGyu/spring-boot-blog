package com.study.blog.post.application.command;

import com.study.blog.post.application.NotFoundPostException;
import com.study.blog.post.domain.Post;
import com.study.blog.post.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePostService {
    private final PostRepository postRepository;

    @Transactional
    public void deletePost(String slug) {
        Post post = postRepository.findBySlug(slug).orElseThrow(NotFoundPostException::new);
        postRepository.deleteById(post.getId());
    }
}
