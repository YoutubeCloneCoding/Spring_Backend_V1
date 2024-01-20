package com.anys34.youtube.domain.file.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

@Service
public class SaveFileService {
    public void execute(byte[] fileData, String filePath, String fileName) {
        File target = new File(filePath, fileName);
        try {
            FileCopyUtils.copy(fileData, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
