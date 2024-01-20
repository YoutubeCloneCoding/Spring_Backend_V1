package com.anys34.youtube.domain.post.service;

import com.anys34.youtube.domain.file.domain.type.FileType;
import com.anys34.youtube.domain.file.service.FileService;
import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.post.domain.repository.PostRepository;
import com.anys34.youtube.domain.post.exception.PostNotFoundException;
import com.anys34.youtube.domain.post.presentation.dto.req.PostSaveRequest;
import com.anys34.youtube.domain.thumbnail.domain.Thumbnail;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostUpdateService {
    private final PostRepository postRepository;
    private final FileService fileService;

    @Transactional
    public void execute(PostSaveRequest postSaveRequest) {
        Post post = postRepository.findById(postSaveRequest.getId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        fileService.makeDir(null, null);

        UUID uuid = UUID.randomUUID();
        MultipartFile file = postSaveRequest.getThumbnail();

        String saveDir = fileService.makeDir(FileType.image, post.getUser().getEmail());
        String fileName = uuid + "_" + file.getOriginalFilename();

        try {
            fileService.saveFile(file.getBytes(), saveDir, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Thumbnail thumbnail = Thumbnail.builder()
                .thumbnailName(fileName)
                .thumbnailPath(saveDir)
                .uuid(uuid)
                .build();

        post.update(postSaveRequest.getTitle(), postSaveRequest.getContents(), postSaveRequest.getPublicScope(), thumbnail);
        thumbnail.updatePost(post);

        postRepository.save(post);
    }
}
