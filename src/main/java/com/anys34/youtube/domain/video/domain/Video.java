package com.anys34.youtube.domain.video.domain;

import com.anys34.youtube.domain.post.domain.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;

    @Column
    private String videoName;

    @Column
    private String videoPath;

    @Column
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Video(String videoName, String videoPath, UUID uuid) {
        this.videoName = videoName;
        this.videoPath = videoPath;
        this.uuid = uuid;
    }

    public void updatePost(Post post) {
        this.post = post;
    }
}
