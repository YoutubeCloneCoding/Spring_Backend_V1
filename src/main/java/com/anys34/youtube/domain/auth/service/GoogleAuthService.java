package com.anys34.youtube.domain.auth.service;

import com.anys34.youtube.domain.auth.presentation.dto.req.AccessTokenRequest;
import com.anys34.youtube.domain.auth.presentation.dto.res.TokenResponse;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.domain.repository.UserRepository;
import com.anys34.youtube.global.feign.auth.GoogleInformationClient;
import com.anys34.youtube.global.feign.auth.dto.res.GoogleInformationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {
    private final GoogleInformationClient googleInformationClient;
    private final UserRepository userRepository;

    @Transactional
    public TokenResponse execute(AccessTokenRequest accessTokenRequest) {
        GoogleInformationResponse response = googleInformationClient.getUserInformation(accessTokenRequest.getAccessToken());

        Optional<User> user = userRepository.findByEmail(response.getEmail());

        if (user.isEmpty()) {
            userRepository.save(User.builder()
                    .email(response.getEmail())
                    .nickname(response.getName())
                    .profileImg(response.getPicture())
                    .build());
        }

        return TokenResponse.builder()
                .accessToken(null)
                .refreshToken(null)
                .build();
    }
}
