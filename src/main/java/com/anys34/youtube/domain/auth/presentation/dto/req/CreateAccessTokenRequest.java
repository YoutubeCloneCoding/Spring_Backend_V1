package com.anys34.youtube.domain.auth.presentation.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateAccessTokenRequest {
    @NotNull(message = "refreshToken이 비었습니다.")
    private String refreshToken;
}
