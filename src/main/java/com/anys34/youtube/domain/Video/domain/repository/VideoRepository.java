package com.anys34.youtube.domain.Video.domain.repository;

import com.anys34.youtube.domain.Post.domain.Post;
import com.anys34.youtube.domain.Video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Video findByPost(Post post);

    Video findByUuidAndPost(UUID uuid, Post post);
}
