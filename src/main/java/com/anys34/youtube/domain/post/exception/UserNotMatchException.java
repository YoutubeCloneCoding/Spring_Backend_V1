package com.anys34.youtube.domain.post.exception;

import com.anys34.youtube.global.config.error.exception.BusinessException;
import com.anys34.youtube.global.config.error.exception.ErrorCode;

public class UserNotMatchException extends BusinessException {
    public static final BusinessException EXCEPTION =
            new UserNotMatchException();

    private UserNotMatchException() {super(ErrorCode.USER_NOT_MATCH);}
}
