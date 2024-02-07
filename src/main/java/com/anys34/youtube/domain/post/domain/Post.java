package com.anys34.youtube.domain.post.domain;

import com.anys34.youtube.domain.post.domain.type.PublicScope;
import com.anys34.youtube.domain.thumbnail.domain.Thumbnail;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.video.domain.Video;
import com.anys34.youtube.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Video video;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Thumbnail thumbnail;

    private PublicScope publicScope;

    @Builder
    public Post(String title, String contents, PublicScope publicScope, User user) {
        this.title = title;
        this.contents = contents;
        this.publicScope = publicScope;
        this.user = user;
    }

    public void update(String title, String contents, PublicScope publicScope, Thumbnail thumbnail) {
        this.title = title;
        this.contents = contents;
        this.publicScope = publicScope;
        this.thumbnail = thumbnail;
    }

    public void updateVideo(Video video) {
        this.video = video;
    }
}
