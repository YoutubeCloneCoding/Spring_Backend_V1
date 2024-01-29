package com.anys34.youtube.domain.post.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostListResponse {
    private String title;
    private String thumbnail;
    private String video;
    private String profile;
    private String nickname;
    private String email;
    private String link;
    private LocalDateTime createdAt;

    @Builder
    public PostListResponse(String title, String thumbnail, String video, String profile, String nickname, String email, String link, LocalDateTime createdAt) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.video = video;
        this.profile = profile;
        this.nickname = nickname;
        this.email = email;
        this.link = link;
        this.createdAt = createdAt;
    }
}
