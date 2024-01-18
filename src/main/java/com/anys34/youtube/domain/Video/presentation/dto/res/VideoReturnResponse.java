package com.anys34.youtube.domain.Video.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VideoReturnResponse {
    private String videoLink;
    private String nickname;
    private String profile;
    private String title;
    private String contents;

    @Builder
    public VideoReturnResponse(String videoLink, String nickname, String profile, String title, String contents) {
        this.videoLink = videoLink;
        this.nickname = nickname;
        this.profile = profile;
        this.title = title;
        this.contents = contents;
    }
}
