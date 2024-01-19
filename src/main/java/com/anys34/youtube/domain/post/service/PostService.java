package com.anys34.youtube.domain.post.service;

import com.anys34.youtube.domain.file.domain.type.FileType;
import com.anys34.youtube.domain.file.service.FileService;
import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.post.domain.repository.PostRepository;
import com.anys34.youtube.domain.post.presentation.dto.req.PostSaveRequest;
import com.anys34.youtube.domain.post.presentation.dto.res.PostListResponse;
import com.anys34.youtube.domain.thumbnail.domain.Thumbnail;
import com.anys34.youtube.domain.thumbnail.domain.repository.ThumbnailRepository;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.domain.repository.UserRepository;
import com.anys34.youtube.domain.video.domain.Video;
import com.anys34.youtube.domain.video.domain.repository.VideoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final ThumbnailRepository thumbnailRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    @Transactional
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

    @Transactional
    public List<PostListResponse> getList(String email, Principal principal) {
        List<Post> posts = null;
        try {
            if (principal == null)
                posts = postRepository.findAllPublicList();
            else {
                    if (email.equals(principal.getName()))
                        posts = postRepository.findByUserListAll(userRepository.findByEmail(principal.getName())
                                .orElseThrow(() -> new IllegalArgumentException("Not Found User")));
                    else
                        posts = postRepository.findByUserList(userRepository.findByEmail(email)
                                .orElseThrow(() -> new IllegalArgumentException("Not Found User")));
            }
        } catch (Exception e) {
            posts = postRepository.findByUserList(userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("Not Found User")));
        }

        return posts.stream()
                .map(post -> {
                    User user = userRepository.findByEmail(post.getUser().getEmail())
                            .orElseThrow(() -> new IllegalArgumentException("Not Found User"));

                    Thumbnail thumbnail = thumbnailRepository.findByPost(post);
                    String thumbnailLink = String.format("https://youtube.anys34.com/%s?email=%s&type=%s", thumbnail.getThumbnailName(), user.getEmail(), FileType.image);

                    Video video = videoRepository.findByPost(post);
                    String link = video.getUuid().toString();

                    return PostListResponse.builder()
                            .title(post.getTitle())
                            .thumbnail(thumbnailLink)
                            .nickname(user.getNickname())
                            .email(user.getEmail())
                            .profile(user.getProfileImg())
                            .link(link)
                            .createdAt(post.getCreateDate())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
