package com.anys34.youtube.domain.Post.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostListResponse {
    private String title;
    private String contents;
    private byte[] thumbnail;
    private String profile;
    private String nickname;

    @Builder
    public PostListResponse(String title, String contents, byte[] thumbnail, String profile, String nickname) {
        this.title = title;
        this.contents = contents;
        this.thumbnail = thumbnail;
        this.profile = profile;
        this.nickname = nickname;
    }
}
