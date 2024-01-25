package com.anys34.youtube.domain.video.service;

import com.anys34.youtube.domain.file.domain.type.FileType;
import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.post.domain.repository.PostRepository;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.domain.repository.UserRepository;
import com.anys34.youtube.domain.user.exception.UserNotFoundException;
import com.anys34.youtube.domain.user.facade.UserFacade;
import com.anys34.youtube.domain.video.domain.Video;
import com.anys34.youtube.domain.video.domain.repository.VideoRepository;
import com.anys34.youtube.domain.video.presentation.dto.res.VideoReturnResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class VideoInfoService {
    private final VideoRepository videoRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    @Transactional
    public VideoReturnResponse execute(UUID uuid, String email) {
        Post post = null;
        User loginUser = userFacade.getCurrentUser();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        Video video = videoRepository.findByUuid(uuid);
        try {
            if (loginUser.getEmail().equals(email)) { // 로그인한 유저와 찾는 게시물의 유저가 같은 경우(모든 게시물 찾기)
                post = postRepository.findByAllUserVideo(user, video);
            } else { // 로그인은 했지만 찾는 게시물의 유저가 다른 경우(공개된 게시물만 찾기)
                post = postRepository.findByPublicUserVideo(user, video);
            }
        } catch (NullPointerException e) { // 로그인이 되어있지 않은 경우(공개된 게시물만 찾기)
            post = postRepository.findByPublicUserVideo(user, video);
        }

        return VideoReturnResponse.builder()
                .videoLink(post.getVideo().getVideoUrl())
                .nickname(user.getNickname())
                .profile(user.getProfileImg())
                .title(post.getTitle())
                .contents(post.getContents())
                .build();
    }
}
