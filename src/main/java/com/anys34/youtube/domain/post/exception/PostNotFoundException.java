package com.anys34.youtube.domain.post.exception;

import com.anys34.youtube.global.config.error.exception.BusinessException;
import com.anys34.youtube.global.config.error.exception.ErrorCode;

public class PostNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION =
            new PostNotFoundException();

    private PostNotFoundException() {super(ErrorCode.POST_NOT_FOUND);}
}
