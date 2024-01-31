package com.anys34.youtube.global.security.principle;

import com.anys34.youtube.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {
    private final UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return (UserDetails) userFacade.getUserByEmail(email);
    }
}
