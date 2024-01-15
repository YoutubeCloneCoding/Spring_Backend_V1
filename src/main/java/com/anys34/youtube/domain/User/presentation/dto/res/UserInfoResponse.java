package com.anys34.youtube.domain.User.presentation.dto.res;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserInfoResponse {
    private String nickname;
    private String email;
    private String profile;

    @Builder
    public UserInfoResponse(String nickname, String email, String profile) {
        this.nickname = nickname;
        this.email = email;
        this.profile = profile;
    }
}
