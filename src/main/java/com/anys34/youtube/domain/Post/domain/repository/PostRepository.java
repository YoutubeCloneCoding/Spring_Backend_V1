package com.anys34.youtube.domain.Post.domain.repository;

import com.anys34.youtube.domain.Post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
