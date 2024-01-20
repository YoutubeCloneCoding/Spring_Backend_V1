package com.anys34.youtube.domain.refreshToken.presentation;

import com.anys34.youtube.domain.refreshToken.presentation.dto.req.CreateAccessTokenRequest;
import com.anys34.youtube.domain.refreshToken.presentation.dto.res.CreateAccessTokenResponse;
import com.anys34.youtube.domain.refreshToken.service.CreateNewAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final CreateNewAccessToken createNewAccessToken;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/token")
    public CreateAccessTokenResponse createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = createNewAccessToken.execute(request.getRefreshToken());
        return new CreateAccessTokenResponse(newAccessToken);
    }
}
