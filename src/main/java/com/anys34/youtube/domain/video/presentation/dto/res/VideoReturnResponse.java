package com.anys34.youtube.domain.video.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class VideoReturnResponse {
    private String videoLink;
    private String nickname;
    private String profile;
    private String title;
    private String contents;
    private LocalDateTime createdAt;

    @Builder
    public VideoReturnResponse(String videoLink, String nickname, String profile, String title, String contents, LocalDateTime createdAt) {
        this.videoLink = videoLink;
        this.nickname = nickname;
        this.profile = profile;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }
}
