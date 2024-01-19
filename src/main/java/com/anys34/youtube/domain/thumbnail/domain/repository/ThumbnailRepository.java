package com.anys34.youtube.domain.thumbnail.domain.repository;

import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.thumbnail.domain.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Long> {
    Thumbnail findByPost(Post post);
}
