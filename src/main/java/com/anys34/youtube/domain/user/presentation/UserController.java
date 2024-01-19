package com.anys34.youtube.domain.user.presentation;

import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.presentation.dto.res.UserInfoResponse;
import com.anys34.youtube.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/profile/{email}")
    public ResponseEntity<UserInfoResponse> profile(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok()
                .body(UserInfoResponse.builder()
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .profile(user.getProfileImg())
                        .build());
    }
}
