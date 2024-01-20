package com.anys34.youtube.domain.refreshToken.presentation.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateAccessTokenRequest {
    private String refreshToken;
}
