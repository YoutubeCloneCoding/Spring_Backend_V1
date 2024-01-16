package com.anys34.youtube.domain.Post.service;

import com.anys34.youtube.domain.Post.domain.Post;
import com.anys34.youtube.domain.Post.domain.repository.PostRepository;
import com.anys34.youtube.domain.Post.presentation.dto.req.PostSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public void update(PostSaveRequest postSaveRequest) {
        Post post = postRepository.findById(postSaveRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("Not Found Post"));
        post.update(postSaveRequest.getTitle(), postSaveRequest.getContents(), postSaveRequest.getPublicScope());
    }
}
