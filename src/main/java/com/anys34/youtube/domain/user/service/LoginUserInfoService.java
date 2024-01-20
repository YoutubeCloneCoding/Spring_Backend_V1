package com.anys34.youtube.domain.user.service;

import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginUserInfoService {
    private final UserFacade userFacade;

    public User execute() {
        return userFacade.getCurrentUser();
    }
}
