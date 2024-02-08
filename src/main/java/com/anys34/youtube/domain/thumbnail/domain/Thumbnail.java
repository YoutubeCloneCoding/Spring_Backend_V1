package com.anys34.youtube.domain.thumbnail.domain;

import com.anys34.youtube.domain.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Thumbnail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String thumbnailUrl;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Thumbnail(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void updatePost(Post post) {
        this.post = post;
    }
}
