package com.anys34.youtube.domain.Video.service;

import com.anys34.youtube.domain.File.domain.type.FileType;
import com.anys34.youtube.domain.File.service.FileService;
import com.anys34.youtube.domain.Post.domain.Post;
import com.anys34.youtube.domain.Post.domain.repository.PostRepository;
import com.anys34.youtube.domain.User.domain.User;
import com.anys34.youtube.domain.Video.domain.Video;
import com.anys34.youtube.domain.Video.domain.repository.VideoRepository;
import com.anys34.youtube.domain.Video.presentation.dto.res.ReturnInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class VideoService {
    private final VideoRepository videoRepository;
    private final PostRepository postRepository;
    private final FileService fileService;

    public ReturnInfoResponse upload(MultipartFile file, User user) {
        fileService.makeDir(null, null);
        UUID uuid = UUID.randomUUID();

        String saveDir = fileService.makeDir(FileType.video, user.getEmail());
        String fileName = uuid + "_" + file.getOriginalFilename();

        try {
            fileService.saveFile(file.getBytes(), saveDir, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Video video = Video.builder()
                .videoName(fileName)
                .videoPath(saveDir)
                .uuid(uuid)
                .build();

        Post post = Post.builder()
                .user(user)
                .build();

        video.setPost(post);
        post.setVideo(video);

        Long postId = postRepository.save(post).getId();
        videoRepository.save(video);
        return ReturnInfoResponse.builder()
                .id(postId)
                .videoName(file.getOriginalFilename().substring(0, fileName.indexOf(".")))
                .originVideoLink(String.format("https://youtube.anys34.com/%s?email=%s&type=%s", fileName, user.getEmail(), FileType.video))
                .videoLink(uuid.toString())
                .build();
    }
}
