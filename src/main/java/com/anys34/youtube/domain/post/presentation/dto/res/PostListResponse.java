package com.anys34.youtube.domain.post.presentation.dto.res;

import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.thumbnail.domain.Thumbnail;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.video.domain.Video;
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

    public PostListResponse(Post post, Thumbnail thumbnail, Video video, User user, String link) {
        this.title = post.getTitle();
        this.thumbnail = thumbnail.getThumbnailUrl();
        this.video = video.getVideoUrl();
        this.profile = user.getProfileImg();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.link = link;
        this.createdAt = post.getCreateDate();
    }
}
