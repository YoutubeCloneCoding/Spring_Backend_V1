package com.anys34.youtube.domain.post.presentation;

import com.anys34.youtube.domain.post.presentation.dto.req.PostSaveRequest;
import com.anys34.youtube.domain.post.presentation.dto.res.PostListResponse;
import com.anys34.youtube.domain.post.service.ListPostService;
import com.anys34.youtube.domain.post.service.UpdatePostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {
    private final UpdatePostService postUpdateService;
    private final ListPostService postListService;

    @PostMapping
    public void update( @Valid @RequestPart PostSaveRequest request,
                        @RequestPart MultipartFile file) {
        postUpdateService.execute(request, file);
    }

    @GetMapping
    public List<PostListResponse> list() {
        return postListService.getList(null);
    }

    @GetMapping("/user")
    public List<PostListResponse> userList(@RequestParam("email") String email) {
        return postListService.getList(email);
    }
}
