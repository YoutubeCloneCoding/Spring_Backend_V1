package com.anys34.youtube.domain.video.service;

import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.post.domain.repository.PostRepository;
import com.anys34.youtube.domain.post.exception.PostNotFoundException;
import com.anys34.youtube.domain.user.domain.User;
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
    private final UserFacade userFacade;

    @Transactional
    public VideoReturnResponse execute(UUID uuid, String email) {
        Post post = null;
        User user = userFacade.getUserByEmail(email);
        Video video = videoRepository.findByUuid(uuid);
        if (!userFacade.isLogin())
            post = postRepository.findByPublicUserVideo(user, video);
        else {
            User loginUser = userFacade.getCurrentUser();
            if (loginUser.getEmail().equals(email))
                post = postRepository.findByAllUserVideo(user, video);
            else
                post = postRepository.findByPublicUserVideo(user, video);
        }

        return VideoReturnResponse.builder()
                .videoLink(post.getVideo().getVideoUrl())
                .nickname(user.getNickname())
                .profile(user.getProfileImg())
                .title(post.getTitle())
                .contents(post.getContents())
                .createdAt(post.getCreateDate())
                .build();
    }
}
