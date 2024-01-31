package com.anys34.youtube.domain.auth.exception;

import com.anys34.youtube.global.config.error.exception.BusinessException;
import com.anys34.youtube.global.config.error.exception.ErrorCode;

public class UnexpectedTokenException extends BusinessException {
    public static final BusinessException EXCEPTION =
            new UnexpectedTokenException();

    private UnexpectedTokenException() {super(ErrorCode.UNEXPECTED_TOKEN);}
}
