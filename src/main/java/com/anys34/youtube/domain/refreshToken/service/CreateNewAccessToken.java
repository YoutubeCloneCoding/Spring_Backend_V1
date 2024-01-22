package com.anys34.youtube.domain.refreshToken.service;

import com.anys34.youtube.domain.refreshToken.domain.repository.RefreshTokenRepository;
import com.anys34.youtube.domain.refreshToken.exception.UnexpectedTokenException;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.service.UserIdFindService;
import com.anys34.youtube.global.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class CreateNewAccessToken {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserIdFindService userIdFindService;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public String execute(String refreshToken) {
        if(!tokenProvider.validToken(refreshToken))
            throw UnexpectedTokenException.EXCEPTION;

        Long userId = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> UnexpectedTokenException.EXCEPTION).getUserId();
        User user = userIdFindService.execute(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}