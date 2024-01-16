package com.anys34.youtube.domain.Thumbnail.domain.repository;

import com.anys34.youtube.domain.Post.domain.Post;
import com.anys34.youtube.domain.Thumbnail.domain.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Long> {
    Thumbnail findByPost(Post post);
}
