package com.anys34.youtube.domain.Video.service;

import com.anys34.youtube.domain.Post.domain.Post;
import com.anys34.youtube.domain.Post.domain.repository.PostRepository;
import com.anys34.youtube.domain.User.domain.User;
import com.anys34.youtube.domain.Video.domain.Video;
import com.anys34.youtube.domain.Video.domain.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
public class VideoService {
    private final VideoRepository videoRepository;
    private final PostRepository postRepository;
    private final String originDir = "src/main/resources/save/";

    public Long upload(MultipartFile file, User user) {
        UUID uuid = UUID.randomUUID();

        String saveDir = makeDir(user.getEmail());
        String saveName = uuid + "_" + file.getOriginalFilename();

        try {
            saveVideo(file.getBytes(), saveDir, saveName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Video video = Video.builder()
                .videoName(saveName)
                .videoPath(saveDir+saveName)
                .uuid(uuid)
                .build();

        Post post = Post.builder()
                .user(user)
                .build();

        video.setPost(post);
        post.setVideo(video);

        Long postId = postRepository.save(post).getId();
        videoRepository.save(video);
        return postId;
    }

    private void saveVideo(byte[] fileData, String filePath, String saveName) {
        File target = new File(filePath, saveName);
        try {
            FileCopyUtils.copy(fileData, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String makeDir(String email) {
        String saveDir = originDir + email + "/video/";
        File Folder = new File(saveDir);

        if (!Folder.exists()) {
            try {
                Folder.mkdir();
                log.info("폴더가 생성되었습니다.");
            } catch (Exception e) {
                e.getStackTrace();
            }
        } else {
            log.info("이미 폴더가 생성되어 있습니다.");
        }
        return saveDir;
    }

    public void makeRootDir() {
        File Folder = new File(originDir);

        if (!Folder.exists()) {
            try {
                Folder.mkdir();
                log.info("폴더가 생성되었습니다.");
            } catch (Exception e) {
                e.getStackTrace();
            }
        } else {
            log.info("이미 폴더가 생성되어 있습니다.");
        }
    }
}
