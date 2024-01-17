package com.anys34.youtube.domain.File.service;

import com.anys34.youtube.domain.File.domain.type.FileType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileService {
    private final String originDir = "src/main/resources/save/";
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

    public Resource showFile(String dirPath, String fileName) {
        Path filePath = Paths.get(dirPath).resolve(fileName);

        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return resource;
    }

    public String getContentType(String fileName) {
        switch (fileName.substring(fileName.indexOf(".")).toLowerCase()) {
            case "jpg":
            case "jpeg":
            case "png":
                return MediaType.IMAGE_JPEG_VALUE;
            case "mp4":
                return "video/mp4";
            default:
                return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }
}
