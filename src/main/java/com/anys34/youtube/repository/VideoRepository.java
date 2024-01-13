package com.anys34.youtube.repository;

import com.anys34.youtube.domain.Video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
