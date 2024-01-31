package com.anys34.youtube.domain.auth.presentation.dto.req;

import lombok.Getter;

@Getter
public class CreateAccessTokenRequest {
    private String refreshToken;
}
