package com.anys34.youtube.domain.user.presentation;

import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.facade.UserFacade;
import com.anys34.youtube.domain.user.presentation.dto.res.UserInfoResponse;
import com.anys34.youtube.domain.user.service.LoginUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final LoginUserInfoService loginUserInfoService;
    private final UserFacade userFacade;

    @GetMapping("/api/profile")
    public UserInfoResponse profile() {
        User user = loginUserInfoService.execute();
        return new UserInfoResponse(user);
    }

    @GetMapping("/profile/{email}")
    public UserInfoResponse profile(@PathVariable String email) {
        return new UserInfoResponse(userFacade.getUserByEmail(email));
    }
}
