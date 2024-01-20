package com.anys34.youtube.domain.file.service;

import com.anys34.youtube.domain.file.exception.ContentTypeNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class GetContentType {
    public String execute(String fileName) {
        switch (fileName.substring(fileName.indexOf(".")).toLowerCase()) {
            case "jpg":
            case "jpeg":
            case "png":
                return MediaType.IMAGE_JPEG_VALUE;
            case "mp4":
                return "video/mp4";
            default:
                throw ContentTypeNotFoundException.EXCEPTION;
        }
    }
}
