package com.anys34.youtube.domain.video.presentation.dto.req;

import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.post.domain.type.PublicScope;
import com.anys34.youtube.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class VideoUploadRequest {
    private MultipartFile video;

    public Post toEntity(User user) {
        return Post.builder()
                .title("")
                .contents("")
                .publicScope(PublicScope.WRITE)
                .user(user)
                .build();
    }
}
