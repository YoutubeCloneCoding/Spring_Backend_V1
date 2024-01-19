package com.anys34.youtube.domain.video.presentation;

import com.anys34.youtube.domain.video.presentation.dto.res.ReturnInfoResponse;
import com.anys34.youtube.domain.video.presentation.dto.res.VideoReturnResponse;
import com.anys34.youtube.domain.video.service.VideoInfoService;
import com.anys34.youtube.domain.video.service.VideoUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class VideoController {
    private final VideoInfoService videoInfoService;
    private final VideoUploadService videoUploadService;

    @PostMapping("/api/upload")
    public ReturnInfoResponse upload(@RequestParam("file") MultipartFile file) {
        return videoUploadService.execute(file);
    }

    @GetMapping("/video/{video}")
    public VideoReturnResponse video(@PathVariable UUID video, @RequestParam("email") String email) {
        return videoInfoService.execute(video, email);
    }
}
