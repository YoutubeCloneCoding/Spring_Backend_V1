package com.anys34.youtube.domain.post.presentation;

import com.anys34.youtube.domain.post.domain.type.PublicScope;
import com.anys34.youtube.domain.post.presentation.dto.req.PostSaveRequest;
import com.anys34.youtube.domain.post.presentation.dto.res.PostListResponse;
import com.anys34.youtube.domain.post.service.PostListService;
import com.anys34.youtube.domain.post.service.PostUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostUpdateService postUpdateService;
    private final PostListService postListService;

    @PostMapping("/api/save")
    public void update( @RequestParam("request") String postSaveRequest,
                        @RequestParam("file") MultipartFile file) {
        postUpdateService.execute(postSaveRequest, file);
    }

    @GetMapping("/list")
    public List<PostListResponse> list() {
        return postListService.getList(null);
    }

    @GetMapping("/userList")
    public List<PostListResponse> userList(@RequestParam("email") String email) {
        return postListService.getList(email);
    }
}
