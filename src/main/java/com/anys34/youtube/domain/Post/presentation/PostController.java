package com.anys34.youtube.domain.Post.presentation;

import com.anys34.youtube.domain.Post.domain.type.PublicScope;
import com.anys34.youtube.domain.Post.presentation.dto.req.PostSaveRequest;
import com.anys34.youtube.domain.Post.presentation.dto.res.PostListResponse;
import com.anys34.youtube.domain.Post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/list")
    public ResponseEntity<List<PostListResponse>> list() {
        List<PostListResponse> response = postService.getList(null, null);
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/userList")
    public ResponseEntity<List<PostListResponse>> userList(@RequestParam("email") String email, Principal principal) {
        List<PostListResponse> responses = postService.getList(email, principal);
        return ResponseEntity.ok()
                .body(responses);
    }
}
