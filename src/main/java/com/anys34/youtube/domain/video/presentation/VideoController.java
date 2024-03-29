package com.anys34.youtube.domain.video.presentation;

import com.anys34.youtube.domain.video.presentation.dto.res.ReturnInfoResponse;
import com.anys34.youtube.domain.video.presentation.dto.res.VideoReturnResponse;
import com.anys34.youtube.domain.video.service.InfoVideoService;
import com.anys34.youtube.domain.video.service.UploadVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/video")
@RestController
public class VideoController {
    private final InfoVideoService videoInfoService;
    private final UploadVideoService videoUploadService;

    @PostMapping
    public ReturnInfoResponse upload(@RequestPart MultipartFile file) {
        return videoUploadService.execute(file);
    }

    @GetMapping("/{video}")
    public VideoReturnResponse video(@PathVariable UUID video, @RequestParam("email") String email) {
        return videoInfoService.execute(video, email);
    }
}
