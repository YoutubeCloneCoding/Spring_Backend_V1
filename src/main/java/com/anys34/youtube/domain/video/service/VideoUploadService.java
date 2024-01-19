package com.anys34.youtube.domain.video.service;

import com.anys34.youtube.domain.file.domain.type.FileType;
import com.anys34.youtube.domain.file.service.FileService;
import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.post.domain.repository.PostRepository;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.domain.repository.UserRepository;
import com.anys34.youtube.domain.user.facade.UserFacade;
import com.anys34.youtube.domain.video.domain.Video;
import com.anys34.youtube.domain.video.domain.repository.VideoRepository;
import com.anys34.youtube.domain.video.presentation.dto.res.ReturnInfoResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VideoUploadService {
    private final VideoRepository videoRepository;
    private final PostRepository postRepository;
    private final FileService fileService;
    private final UserFacade userFacade;

    @Transactional
    public ReturnInfoResponse execute(MultipartFile file) {
        User user = userFacade.getCurrentUser();
        fileService.makeDir(null, null);
        UUID uuid = UUID.randomUUID();

        String saveDir = fileService.makeDir(FileType.video, user.getEmail());
        String fileName = uuid + "_" + file.getOriginalFilename();

        String originName = file.getOriginalFilename();
        String videoName = originName.substring(0, originName.indexOf('.'));

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
                .videoName(videoName)
                .originVideoLink(String.format("https://youtube.anys34.com/%s?email=%s&type=%s", fileName, user.getEmail(), FileType.video))
                .videoLink(uuid)
                .build();
    }
}
