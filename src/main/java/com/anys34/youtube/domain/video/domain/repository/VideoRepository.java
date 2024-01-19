package com.anys34.youtube.domain.video.domain.repository;

import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Video findByPost(Post post);
    Video findByUuid(UUID uuid);
}
