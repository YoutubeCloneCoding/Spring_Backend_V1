package com.anys34.youtube.domain.file.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class SaveFileService {
    public void execute(MultipartFile file, String filePath, String fileName) {
        File target = new File(filePath, fileName);
        try {
            FileCopyUtils.copy(file.getBytes(), target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
