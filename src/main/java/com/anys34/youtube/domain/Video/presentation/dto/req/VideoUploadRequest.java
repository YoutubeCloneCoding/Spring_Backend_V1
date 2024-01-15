package com.anys34.youtube.domain.Video.presentation.dto.req;

import com.anys34.youtube.domain.Post.domain.Post;
import com.anys34.youtube.domain.Post.domain.type.PublicScope;
import com.anys34.youtube.domain.User.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
public class VideoUploadRequest {
    private MultipartFile video;

    public Post toEntity(User user) {
        return Post.builder()
                .title("")
                .contents("")
                .publicScope(PublicScope.PRIVATE)
                .user(user)
                .build();
    }
}
