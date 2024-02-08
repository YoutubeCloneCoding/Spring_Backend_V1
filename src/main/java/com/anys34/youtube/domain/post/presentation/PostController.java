package com.anys34.youtube.domain.post.presentation;

import com.anys34.youtube.domain.post.presentation.dto.req.PostSaveRequest;
import com.anys34.youtube.domain.post.presentation.dto.res.PostListResponse;
import com.anys34.youtube.domain.post.service.PostListService;
import com.anys34.youtube.domain.post.service.PostUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostUpdateService postUpdateService;
    private final PostListService postListService;

    @PostMapping("/api/save")
    public void update( @Valid @RequestPart PostSaveRequest request,
                        @RequestPart MultipartFile file) {
        postUpdateService.execute(request, file);
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
