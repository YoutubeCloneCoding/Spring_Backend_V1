package com.anys34.youtube.domain.auth.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
