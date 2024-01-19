package com.anys34.youtube.domain.file.presentation;

import com.anys34.youtube.domain.file.domain.type.FileType;
import com.anys34.youtube.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FileController {
    @Value("${spring.save.dir}")
    private String originDir;

    private final FileService fileService;

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> showFile(@RequestParam(value = "email") String email,
                                             @RequestParam(value = "type") FileType fileType,
                                             @PathVariable String fileName) {
        String dirPath = originDir + email + "/" + fileType;
        Resource resource = fileService.showFile(dirPath, fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName);
        String contentType = fileService.getContentType(fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
