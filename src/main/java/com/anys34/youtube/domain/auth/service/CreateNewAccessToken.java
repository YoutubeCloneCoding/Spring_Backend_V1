package com.anys34.youtube.domain.auth.service;

import com.anys34.youtube.domain.auth.domain.repository.RefreshTokenRepository;
import com.anys34.youtube.domain.auth.exception.UnexpectedTokenException;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.domain.repository.UserRepository;
import com.anys34.youtube.domain.user.exception.UserNotFoundException;
import com.anys34.youtube.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class CreateNewAccessToken {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public String execute(String refreshToken) {
        if(!tokenProvider.validToken(refreshToken))
            throw UnexpectedTokenException.EXCEPTION;

        Long userId = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(() -> UnexpectedTokenException.EXCEPTION).getEmail();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
