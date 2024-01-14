package com.anys34.youtube.domain.Thumbnail.domain;

import com.anys34.youtube.domain.Post.domain.Post;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Thumbnail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumbnail_id")
    private Long id;

    @Column
    private String thumbnailName;

    @Column
    private String thumbnailPath;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
