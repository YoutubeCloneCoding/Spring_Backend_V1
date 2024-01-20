package com.anys34.youtube.domain.file.exception;

import com.anys34.youtube.global.config.error.exception.BusinessException;
import com.anys34.youtube.global.config.error.exception.ErrorCode;

public class ContentTypeNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION =
            new ContentTypeNotFoundException();

    private ContentTypeNotFoundException() {super(ErrorCode.CONTENT_TYPE_NOT_FOUND);}
}
