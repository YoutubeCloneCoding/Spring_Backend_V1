package com.anys34.youtube.domain.Video.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReturnInfoResponse {
    private Long id;
    private String videoName;
    private String originVideoLink;
    private String videoLink;

    @Builder
    public ReturnInfoResponse(Long id, String videoName, String originVideoLink, String videoLink) {
        this.id = id;
        this.videoName = videoName;
        this.originVideoLink = originVideoLink;
        this.videoLink = videoLink;
    }
}
