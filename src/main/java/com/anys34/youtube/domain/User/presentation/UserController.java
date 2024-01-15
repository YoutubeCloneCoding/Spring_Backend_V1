package com.anys34.youtube.domain.User.presentation;

import com.anys34.youtube.domain.User.domain.User;
import com.anys34.youtube.domain.User.presentation.dto.res.UserInfoResponse;
import com.anys34.youtube.domain.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/api/profile")
    public ResponseEntity<UserInfoResponse> profile(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok()
                .body(UserInfoResponse.builder()
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .profile(user.getProfileImg())
                        .build());
    }
}
