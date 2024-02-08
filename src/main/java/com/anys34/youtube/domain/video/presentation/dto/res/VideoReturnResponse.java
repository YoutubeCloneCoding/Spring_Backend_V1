package com.anys34.youtube.domain.video.presentation.dto.res;

import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.user.domain.User;
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

    public VideoReturnResponse(Post post, User user) {
        this.videoLink = post.getVideo().getVideoUrl();
        this.nickname = user.getNickname();
        this.profile = user.getProfileImg();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createdAt = post.getCreateDate();
    }
}
