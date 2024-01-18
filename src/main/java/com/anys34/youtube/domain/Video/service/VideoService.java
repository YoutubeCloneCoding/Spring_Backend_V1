package com.anys34.youtube.domain.Video.service;

import com.anys34.youtube.domain.File.domain.type.FileType;
import com.anys34.youtube.domain.File.service.FileService;
import com.anys34.youtube.domain.Post.domain.Post;
import com.anys34.youtube.domain.Post.domain.repository.PostRepository;
import com.anys34.youtube.domain.User.domain.User;
import com.anys34.youtube.domain.User.domain.repository.UserRepository;
import com.anys34.youtube.domain.Video.domain.Video;
import com.anys34.youtube.domain.Video.domain.repository.VideoRepository;
import com.anys34.youtube.domain.Video.presentation.dto.res.ReturnInfoResponse;
import com.anys34.youtube.domain.Video.presentation.dto.res.VideoReturnResponse;
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
public class VideoService {
    private final VideoRepository videoRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
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
                .videoLink(uuid)
                .build();
    }

    public VideoReturnResponse info(UUID video, String email, Principal principal) {
        List<Post> posts = null;
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        try {
            if (principal.getName().equals(email)) { // 로그인한 유저와 찾는 게시물의 유저가 같은 경우(모든 게시물 찾기)
                posts = postRepository.findByUserListAll(user);
            } else { // 로그인은 했지만 찾는 게시물의 유저가 다른 경우(공개된 게시물만 찾기)
                posts = postRepository.findByUserList(user);
            }
        } catch (NullPointerException e) { // 로그인이 되어있지 않은 경우(공개된 게시물만 찾기)
            posts = postRepository.findByUserList(user);
        }

        return posts.stream()
                .map(post -> {
                    Video findVideo = videoRepository.findByUuidAndPost(video, post);

                    return VideoReturnResponse.builder()
                            .videoLink(String.format("http://localhost:8080/%s?email=%s&type=%s", findVideo.getVideoName(), email, FileType.video))
                            .nickname(user.getNickname())
                            .profile(user.getProfileImg())
                            .title(post.getTitle())
                            .contents(post.getContents())
                            .build();
                })
                .toList().get(0);
    }
}
