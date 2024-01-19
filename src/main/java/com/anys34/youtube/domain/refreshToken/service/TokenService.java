package com.anys34.youtube.domain.refreshToken.service;

import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.service.UserIdFindService;
import com.anys34.youtube.global.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserIdFindService userIdFindService;

    public String createNewAccessToken(String refreshToken) {
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userIdFindService.execute(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
