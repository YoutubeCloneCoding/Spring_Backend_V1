package com.anys34.youtube.domain.post.presentation.dto.req;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class PostSaveRequest {
    private String id;
    private String title;
    private String contents;
    private String publicScope;
}
