package com.anys34.youtube.domain.post.service;

import com.anys34.youtube.domain.post.domain.Post;
import com.anys34.youtube.domain.post.domain.repository.PostRepository;
import com.anys34.youtube.domain.post.presentation.dto.res.PostListResponse;
import com.anys34.youtube.domain.thumbnail.domain.Thumbnail;
import com.anys34.youtube.domain.thumbnail.domain.repository.ThumbnailRepository;
import com.anys34.youtube.domain.user.domain.User;
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
public class ListPostService {
    private final PostRepository postRepository;
    private final ThumbnailRepository thumbnailRepository;
    private final VideoRepository videoRepository;
    private final UserFacade userFacade;

    @Transactional
    public List<PostListResponse> getList(String email) {
        List<Post> posts = null;
        if (!userFacade.isLogin() || email == null)
            posts = postRepository.findAllPublicList();
        else {
            User user = userFacade.getCurrentUser();
            if (email.equals(user.getEmail()))
                posts = postRepository.findByUserListAll(userFacade.getUserByEmail(user.getEmail()));
            else
                posts = postRepository.findByUserList(userFacade.getUserByEmail(email));
        }

        return posts.stream()
                .map(post -> {
                    User user = userFacade.getUserByEmail(post.getUser().getEmail());

                    Thumbnail thumbnail = thumbnailRepository.findByPost(post);

                    Video video = videoRepository.findByPost(post);

                    String link = video.getUuid().toString();

                    return new PostListResponse(post, thumbnail, video, user, link);
                })
                .collect(Collectors.toList());
    }
}
