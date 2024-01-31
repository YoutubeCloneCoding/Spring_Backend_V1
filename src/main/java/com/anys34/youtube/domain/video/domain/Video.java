package com.anys34.youtube.domain.video.domain;

import com.anys34.youtube.domain.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;

    @Column
    private String videoUrl;

    @Column
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Video(String videoUrl, UUID uuid) {
        this.videoUrl = videoUrl;
        this.uuid = uuid;
    }

    public void updatePost(Post post) {
        this.post = post;
    }
}
