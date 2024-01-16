package com.anys34.youtube.domain.File.service;

import com.anys34.youtube.domain.File.domain.type.FileType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileService {
    private String originDir = "src/main/resources/save/";
    public String makeDir(FileType type, String email) {
        String saveDir = originDir + email + "/";
        try {
            File file = new File(originDir);
            if(!file.exists())
                file.mkdir();
            file = new File(saveDir);
            if(type != null && email != null) {
                if(!file.exists())
                    file.mkdir();
                saveDir += type + "/";
                file = new File(saveDir);
                if(!file.exists())
                    file.mkdir();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return saveDir;
    }

    public void saveFile(byte[] fileData, String filePath, String fileName) {
        File target = new File(filePath, fileName);
        try {
            FileCopyUtils.copy(fileData, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
