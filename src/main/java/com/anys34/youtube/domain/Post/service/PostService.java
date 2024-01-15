package com.anys34.youtube.domain.Post.service;

import com.anys34.youtube.domain.Post.domain.repository.PostRepository;
import com.anys34.youtube.domain.Post.presentation.dto.req.PostSaveRequest;
import com.anys34.youtube.domain.User.domain.User;
import com.anys34.youtube.domain.User.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void save(PostSaveRequest postSaveRequest, String user) {
        System.out.println(user);
    }
}
