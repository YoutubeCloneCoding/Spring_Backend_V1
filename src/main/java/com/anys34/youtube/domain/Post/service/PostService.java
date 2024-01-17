package com.anys34.youtube.domain.Post.service;

import com.anys34.youtube.domain.File.domain.type.FileType;
import com.anys34.youtube.domain.File.service.FileService;
import com.anys34.youtube.domain.Post.domain.Post;
import com.anys34.youtube.domain.Post.domain.repository.PostRepository;
import com.anys34.youtube.domain.Post.presentation.dto.req.PostSaveRequest;
import com.anys34.youtube.domain.Post.presentation.dto.res.PostListResponse;
import com.anys34.youtube.domain.Thumbnail.domain.Thumbnail;
import com.anys34.youtube.domain.Thumbnail.domain.repository.ThumbnailRepository;
import com.anys34.youtube.domain.User.domain.User;
import com.anys34.youtube.domain.User.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ThumbnailRepository thumbnailRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    public void update(PostSaveRequest postSaveRequest) {
        Post post = postRepository.findById(postSaveRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("Not Found Post"));

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
        thumbnail.setPost(post);

        postRepository.save(post);
    }

    public List<PostListResponse> getList() {
        List<Post> posts = postRepository.findAllWithTitleNotNull();

        return posts.stream()
                .map(post -> {
                    Thumbnail thumbnail = thumbnailRepository.findByPost(post);
                    String thumbnailFile = null;

                    User user = userRepository.findByEmail(post.getUser().getEmail())
                            .orElseThrow(() -> new IllegalArgumentException("Not Found User"));

                    return PostListResponse.builder()
                            .title(post.getTitle())
                            .contents(post.getContents())
                            .thumbnail(thumbnailFile)
                            .nickname(user.getNickname())
                            .profile(user.getProfileImg())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
