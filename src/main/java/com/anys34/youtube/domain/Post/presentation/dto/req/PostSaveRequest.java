package com.anys34.youtube.domain.Post.presentation.dto.req;

import com.anys34.youtube.domain.Post.domain.Post;
import com.anys34.youtube.domain.Post.domain.type.PublicScope;
import com.anys34.youtube.domain.User.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
public class PostSaveRequest {
    private String title;
    private String contents;
    private PublicScope publicScope;
    private MultipartFile thumbnail;

    public Post toEntity(User user) {
        return Post.builder()
                .title(title)
                .contents(contents)
                .publicScope(publicScope)
                .user(user)
                .build();
    }
}
