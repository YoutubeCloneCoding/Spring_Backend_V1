package com.anys34.youtube.domain.Post.domain.repository;

import com.anys34.youtube.domain.Post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.title IS NOT NULL")
    List<Post> findAllWithTitleNotNull();
}
