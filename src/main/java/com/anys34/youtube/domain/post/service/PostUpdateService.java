package com.anys34.youtube.domain.post.service;

import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.post.domain.repository.PostRepository;
import com.anys34.youtube.domain.post.domain.type.FileType;
import com.anys34.youtube.domain.post.domain.type.PublicScope;
import com.anys34.youtube.domain.post.exception.PostNotFoundException;
import com.anys34.youtube.domain.post.exception.UserNotMatchException;
import com.anys34.youtube.domain.post.presentation.dto.req.PostSaveRequest;
import com.anys34.youtube.domain.thumbnail.domain.Thumbnail;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.facade.UserFacade;
import com.anys34.youtube.infrastructure.s3.service.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostUpdateService {
    private final PostRepository postRepository;
    private final UserFacade userFacade;
    private final S3Service s3Service;

    @Transactional
    public void execute(PostSaveRequest postSaveRequest, MultipartFile file) {
        Post post = postRepository.findById(Long.valueOf(postSaveRequest.getId()))
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        User user = userFacade.getCurrentUser();

        if (user != post.getUser())
            throw UserNotMatchException.EXCEPTION;

        UUID uuid = UUID.randomUUID();

        String fileUrl = s3Service.uploadFile(file, user.getEmail(), FileType.image, uuid);

        Thumbnail thumbnail = Thumbnail.builder()
                .thumbnailUrl(fileUrl)
                .uuid(uuid)
                .build();

        post.update(postSaveRequest.getTitle(), postSaveRequest.getContents(), PublicScope.valueOf(postSaveRequest.getPublicScope()), thumbnail);
        thumbnail.updatePost(post);
    }
}
