package com.anys34.youtube.domain.Video.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReturnInfoResponse {
    private Long id;
    private String videoName;

    @Builder
    public ReturnInfoResponse(Long id, String videoName) {
        this.id = id;
        this.videoName = videoName;
    }
}
