package com.anys34.youtube.domain.video.presentation;

import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.facade.UserFacade;
import com.anys34.youtube.domain.user.service.LoginUserInfoService;
import com.anys34.youtube.domain.video.presentation.dto.res.ReturnInfoResponse;
import com.anys34.youtube.domain.video.presentation.dto.res.VideoReturnResponse;
import com.anys34.youtube.domain.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class VideoController {
    private final VideoService videoService;
    private final UserFacade userFacade;

    @PostMapping("/api/upload")
    public ResponseEntity<ReturnInfoResponse> upload(@RequestParam("file") MultipartFile file) {
        User user = userFacade.getCurrentUser();
        ReturnInfoResponse response = videoService.upload(file, user);
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/video/{video}")
    public ResponseEntity<VideoReturnResponse> video(@PathVariable UUID video, @RequestParam("email") String email, Principal principal) {
        VideoReturnResponse response = videoService.info(video, email, principal);
        return ResponseEntity.ok()
                .body(response);
    }
}
