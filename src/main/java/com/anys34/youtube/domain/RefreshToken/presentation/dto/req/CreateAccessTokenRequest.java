package com.anys34.youtube.domain.RefreshToken.presentation.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenRequest {
    private String refreshToken;
}
