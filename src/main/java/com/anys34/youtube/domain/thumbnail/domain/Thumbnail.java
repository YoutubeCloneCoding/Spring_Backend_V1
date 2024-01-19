package com.anys34.youtube.domain.thumbnail.domain;

import com.anys34.youtube.domain.post.domain.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String thumbnailName;

    @Column
    private String thumbnailPath;

    @Column
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Thumbnail(String thumbnailName, String thumbnailPath, UUID uuid) {
        this.thumbnailName = thumbnailName;
        this.thumbnailPath = thumbnailPath;
        this.uuid = uuid;
    }

    public void updatePost(Post post) {
        this.post = post;
    }
}
