package com.anys34.youtube.domain.video.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ReturnInfoResponse {
    private Long id;
    private String videoUrl;
    private UUID videoLink;

    @Builder
    public ReturnInfoResponse(Long id, String videoUrl, UUID videoLink) {
        this.id = id;
        this.videoUrl = videoUrl;
        this.videoLink = videoLink;
    }
}
