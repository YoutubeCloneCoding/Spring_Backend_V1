package com.anys34.youtube.domain.video.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ReturnInfoResponse {
    private Long id;
    private String videoName;
    private String originVideoLink;
    private UUID videoLink;

    @Builder
    public ReturnInfoResponse(Long id, String videoName, String originVideoLink, UUID videoLink) {
        this.id = id;
        this.videoName = videoName;
        this.originVideoLink = originVideoLink;
        this.videoLink = videoLink;
    }
}
