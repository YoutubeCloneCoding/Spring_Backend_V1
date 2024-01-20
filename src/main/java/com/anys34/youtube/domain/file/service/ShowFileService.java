package com.anys34.youtube.domain.file.service;

import com.anys34.youtube.domain.file.domain.type.FileType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ShowFileService {
    @Value("${spring.save.dir}")
    private String originDir;

    public Resource execute(String email, FileType fileType, String fileName) {
        String dirPath = originDir + email + "/" + fileType;
        Path filePath = Paths.get(dirPath).resolve(fileName);

        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return resource;
    }
}
