package com.anys34.youtube.domain.post.presentation.dto.req;

import com.anys34.youtube.domain.post.domain.type.PublicScope;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequest {
    private Long id;
    private String title;
    private String contents;
    private PublicScope publicScope;
}
