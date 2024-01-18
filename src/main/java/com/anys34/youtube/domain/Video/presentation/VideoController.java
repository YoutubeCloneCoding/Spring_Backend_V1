package com.anys34.youtube.domain.Video.presentation;

import com.anys34.youtube.domain.User.domain.User;
import com.anys34.youtube.domain.User.service.UserService;
import com.anys34.youtube.domain.Video.presentation.dto.res.ReturnInfoResponse;
import com.anys34.youtube.domain.Video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class VideoController {
    private final VideoService videoService;
    private final UserService userService;

    @PostMapping("/api/upload")
    public ResponseEntity<ReturnInfoResponse> upload(@RequestParam("file") MultipartFile file, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        String fileName = file.getOriginalFilename();
        return ResponseEntity.ok()
                .body(ReturnInfoResponse.builder()
                        .id(videoService.upload(file, user))
                        .videoName(fileName.substring(0, fileName.indexOf(".")))
                        .build());
    }
}