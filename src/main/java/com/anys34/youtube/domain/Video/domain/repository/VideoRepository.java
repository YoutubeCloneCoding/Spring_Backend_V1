package com.anys34.youtube.domain.Video.domain.repository;

import com.anys34.youtube.domain.Video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}