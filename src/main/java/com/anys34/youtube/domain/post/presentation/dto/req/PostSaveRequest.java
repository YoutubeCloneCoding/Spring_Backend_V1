package com.anys34.youtube.domain.post.presentation.dto.req;

import com.anys34.youtube.domain.post.domain.type.PublicScope;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class PostSaveRequest {
    private Long id;
    private String title;
    private String contents;
    private PublicScope publicScope;
    private MultipartFile thumbnail;

    @Builder
    public PostSaveRequest(Long id, String title, String contents, PublicScope publicScope, MultipartFile thumbnail) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.publicScope = publicScope;
        this.thumbnail = thumbnail;
    }
}
