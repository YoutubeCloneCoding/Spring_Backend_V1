package com.anys34.youtube.domain.post.domain.repository;

import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.title IS NOT NULL AND p.publicScope = com.anys34.youtube.domain.post.domain.type.PublicScope.PUBLIC")
    Optional<List<Post>> findAllPublicList();

    @Query("SELECT p FROM Post p WHERE p.title IS NOT NULL AND p.user = :user")
    Optional<List<Post>> findByUserListAll(@Param("user") User user);

    @Query("SELECT p FROM Post p WHERE p.title IS NOT NULL AND p.user = :user AND p.video = :video")
    Post findByAllUserVideo(@Param("user") User user, @Param("video") Video video);

    @Query("SELECT p FROM Post p WHERE p.title IS NOT NULL AND p.user = :user AND p.publicScope = com.anys34.youtube.domain.post.domain.type.PublicScope.PUBLIC")
    Optional<List<Post>> findByUserList(@Param("user") User user);

    @Query("SELECT p FROM Post p WHERE p.title IS NOT NULL AND p.user = :user AND p.publicScope != com.anys34.youtube.domain.post.domain.type.PublicScope.PRIVATE AND p.video = :video")
    Post findByPublicUserVideo(@Param("user") User user, @Param("video") Video video);
}
