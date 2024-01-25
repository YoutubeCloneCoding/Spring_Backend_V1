package com.anys34.youtube.domain.thumbnail.domain;

import com.anys34.youtube.domain.post.domain.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Thumbnail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumbnail_id")
    private Long id;

    @Column
    private String thumbnailUrl;

    @Column
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Thumbnail(String thumbnailUrl, UUID uuid) {
        this.thumbnailUrl = thumbnailUrl;
        this.uuid = uuid;
    }

    public void updatePost(Post post) {
        this.post = post;
    }
}
