package com.anys34.youtube.domain.Post.presentation;

import com.anys34.youtube.domain.Post.presentation.dto.req.PostSaveRequest;
import com.anys34.youtube.domain.Post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

//    @PostMapping("/")
//    public void save(@RequestBody PostSaveRequest postSaveRequest, Principal principal) {
//        postService.save(postSaveRequest, principal.getName());
//    }
}
