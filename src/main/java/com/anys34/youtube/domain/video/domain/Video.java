package com.anys34.youtube.domain.video.domain;

import com.anys34.youtube.domain.Post.domain.Post;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

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

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;
}