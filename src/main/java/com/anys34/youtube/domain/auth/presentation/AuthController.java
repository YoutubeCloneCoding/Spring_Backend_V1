package com.anys34.youtube.domain.auth.presentation;

import com.anys34.youtube.domain.auth.presentation.dto.req.AccessTokenRequest;
import com.anys34.youtube.domain.auth.presentation.dto.req.CreateAccessTokenRequest;
import com.anys34.youtube.domain.auth.presentation.dto.res.AccessTokenResponse;
import com.anys34.youtube.domain.auth.presentation.dto.res.TokenResponse;
import com.anys34.youtube.domain.auth.service.CreateAccessTokenService;
import com.anys34.youtube.domain.auth.service.GoogleAuthLinkService;
import com.anys34.youtube.domain.auth.service.GoogleAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final CreateAccessTokenService createNewAccessToken;
    private final GoogleAuthLinkService googleAuthLinkService;
    private final GoogleAuthService googleAuthService;

    @GetMapping
    public String getGoogleAuthLink() {
        return googleAuthLinkService.execute();
    }

    @PostMapping
    public TokenResponse login(@RequestBody @Valid AccessTokenRequest accessTokenRequest) {
        return googleAuthService.execute(accessTokenRequest.getAccessToken());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/token")
    public AccessTokenResponse createNewAccessToken(@RequestBody @Valid CreateAccessTokenRequest request) {
        return createNewAccessToken.execute(request.getRefreshToken());
    }
}
