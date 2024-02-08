package com.anys34.youtube.domain.auth.presentation;

import com.anys34.youtube.domain.auth.presentation.dto.req.AccessTokenRequest;
import com.anys34.youtube.domain.auth.presentation.dto.req.CreateAccessTokenRequest;
import com.anys34.youtube.domain.auth.presentation.dto.res.CreateAccessTokenResponse;
import com.anys34.youtube.domain.auth.presentation.dto.res.TokenResponse;
import com.anys34.youtube.domain.auth.service.CreateNewAccessToken;
import com.anys34.youtube.domain.auth.service.GoogleAuthLinkService;
import com.anys34.youtube.domain.auth.service.GoogleAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class TokenApiController {
    private final CreateNewAccessToken createNewAccessToken;
    private final GoogleAuthLinkService googleAuthLinkService;
    private final GoogleAuthService googleAuthService;

    @GetMapping
    public String getGoogleAuthLink() {
        return googleAuthLinkService.execute();
    }

    @PostMapping
    public TokenResponse login(@RequestBody @Valid AccessTokenRequest accessTokenRequest) {
        return googleAuthService.execute(accessTokenRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/token")
    public CreateAccessTokenResponse createNewAccessToken(@RequestBody @Valid CreateAccessTokenRequest request) {
        String newAccessToken = createNewAccessToken.execute(request.getRefreshToken());
        return new CreateAccessTokenResponse(newAccessToken);
    }
}
