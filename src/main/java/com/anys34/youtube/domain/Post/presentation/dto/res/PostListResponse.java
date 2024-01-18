package com.anys34.youtube.domain.Post.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostListResponse {
    private String title;
    private String thumbnail;
    private String profile;
    private String nickname;
    private String link;
    private LocalDateTime createdAt;

    @Builder
    public PostListResponse(String title, String thumbnail, String profile, String nickname, String link, LocalDateTime createdAt) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.profile = profile;
        this.nickname = nickname;
        this.link = link;
        this.createdAt = createdAt;
    }
}
