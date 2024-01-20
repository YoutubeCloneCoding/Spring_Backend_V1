package com.anys34.youtube.domain.post.service;

import com.anys34.youtube.domain.file.domain.type.FileType;
import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.post.domain.repository.PostRepository;
import com.anys34.youtube.domain.post.exception.PostNotFoundException;
import com.anys34.youtube.domain.post.presentation.dto.res.PostListResponse;
import com.anys34.youtube.domain.thumbnail.domain.Thumbnail;
import com.anys34.youtube.domain.thumbnail.domain.repository.ThumbnailRepository;
import com.anys34.youtube.domain.user.domain.User;
import com.anys34.youtube.domain.user.domain.repository.UserRepository;
import com.anys34.youtube.domain.user.exception.UserNotFoundException;
import com.anys34.youtube.domain.user.facade.UserFacade;
import com.anys34.youtube.domain.video.domain.Video;
import com.anys34.youtube.domain.video.domain.repository.VideoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostListService {
    private final PostRepository postRepository;
    private final ThumbnailRepository thumbnailRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    @Transactional
    public List<PostListResponse> getList(String email) {
        List<Post> posts = null;
        if (!userFacade.isLogin())
            posts = postRepository.findAllPublicList()
                    .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        else {
            User user = userFacade.getCurrentUser();
            if (email.equals(user.getEmail()))
                posts = postRepository.findByUserListAll(userRepository.findByEmail(user.getEmail())
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION))
                        .orElseThrow(() -> PostNotFoundException.EXCEPTION);
            else
                posts = postRepository.findByUserList(userRepository.findByEmail(email)
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION))
                        .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        }

        return posts.stream()
                .map(post -> {
                    User user = userRepository.findByEmail(post.getUser().getEmail())
                            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

                    Thumbnail thumbnail = thumbnailRepository.findByPost(post)
                            .orElseThrow(() -> PostNotFoundException.EXCEPTION);


                    String thumbnailLink = String.format("https://youtube.anys34.com/%s?email=%s&type=%s", thumbnail.getThumbnailName(), user.getEmail(), FileType.image);

                    Video video = videoRepository.findByPost(post)
                            .orElseThrow(() -> PostNotFoundException.EXCEPTION);

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
