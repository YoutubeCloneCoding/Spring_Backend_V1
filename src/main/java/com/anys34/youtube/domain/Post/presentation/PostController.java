package com.anys34.youtube.domain.Post.presentation;

import com.anys34.youtube.domain.Post.domain.type.PublicScope;
import com.anys34.youtube.domain.Post.presentation.dto.req.PostSaveRequest;
import com.anys34.youtube.domain.Post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/api/save")
    public void update( @RequestParam("id") Long id,
                        @RequestParam("title") String title,
                        @RequestParam("contents") String contents,
                        @RequestParam("publicScope") PublicScope publicScope,
                        @RequestParam("thumbnail") MultipartFile thumbnail) {
        postService.update(PostSaveRequest.builder()
                        .id(id)
                        .title(title)
                        .contents(contents)
                        .publicScope(publicScope)
                        .thumbnail(thumbnail)
                        .build());
    }
}
