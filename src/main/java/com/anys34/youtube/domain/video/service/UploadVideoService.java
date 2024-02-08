package com.anys34.youtube.domain.video.service;

import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.post.domain.repository.PostRepository;
import com.anys34.youtube.domain.post.domain.type.FileType;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.facade.UserFacade;
import com.anys34.youtube.domain.video.domain.Video;
import com.anys34.youtube.domain.video.presentation.dto.res.ReturnInfoResponse;
import com.anys34.youtube.infrastructure.s3.service.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UploadVideoService {
    private final PostRepository postRepository;
    private final S3Service s3Service;
    private final UserFacade userFacade;

    @Transactional
    public ReturnInfoResponse execute(MultipartFile file) {
        User user = userFacade.getCurrentUser();
        UUID uuid = UUID.randomUUID();

        String videoUrl = s3Service.uploadFile(file, user.getEmail(), FileType.VIDEO, uuid);

        Video video = new Video(videoUrl, uuid);
        Post post = new Post(user);

        video.updatePost(post);
        post.updateVideo(video);

        Long postId = postRepository.save(post).getId();

        return new ReturnInfoResponse(postId, videoUrl, uuid);
    }
}
