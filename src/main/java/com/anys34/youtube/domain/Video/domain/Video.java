package com.anys34.youtube.domain.Video.domain;

import com.anys34.youtube.domain.Video.domain.type.PublicScope;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id", updatable = false)
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String video;

    @Column
    private String thumbnail;

    @Column
    private PublicScope publicScope;
}
