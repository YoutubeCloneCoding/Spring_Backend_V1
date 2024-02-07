package com.anys34.youtube.domain.post.presentation.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostSaveRequest {
    @Min(1)
    @NotNull(message = "id가 비어있습니다.")
    private String id;
    @NotNull(message = "title이 비어있습니다.")
    private String title;
    @NotNull(message = "contents가 비어있습니다.")
    private String contents;
    @NotNull(message = "publicScope가 비어있습니다.")
    private String publicScope;
}
