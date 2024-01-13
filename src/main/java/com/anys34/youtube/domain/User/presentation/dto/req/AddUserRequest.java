package com.anys34.youtube.domain.User.presentation.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;
}
