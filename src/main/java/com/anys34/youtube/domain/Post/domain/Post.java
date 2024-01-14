package com.anys34.youtube.domain.Post.domain;

import com.anys34.youtube.domain.Post.domain.type.PublicScope;
import com.anys34.youtube.domain.Thumbnail.domain.Thumbnail;
import com.anys34.youtube.domain.User.domain.User;
import com.anys34.youtube.domain.video.domain.Video;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Video video;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Thumbnail thumbnail;

    @Column
    private PublicScope publicScope;
}
