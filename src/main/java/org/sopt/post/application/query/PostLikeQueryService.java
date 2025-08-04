package org.sopt.post.application.query;

import static org.sopt.post.domain.exception.PostErrorCode.POST_NOT_FOUND;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.post.application.dto.response.GetPostLikesServiceResponse;
import org.sopt.post.application.dto.response.GetUsersLikedServiceResponse;
import org.sopt.post.application.dto.response.LikeCountServiceResponse;
import org.sopt.post.domain.PostLike;
import org.sopt.post.domain.exception.PostException;
import org.sopt.post.infrastructure.repository.PostLikeRepository;
import org.sopt.post.infrastructure.repository.PostRepository;
import org.sopt.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostLikeQueryService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public GetPostLikesServiceResponse getPostLikes(Long postId) {
        validatePostExists(postId);

        int count = getPostLikeCount(postId);
        List<User> users = getPostLikeUserList(postId);

        return GetPostLikesServiceResponse.from(count, users);
    }

    public LikeCountServiceResponse getLikeCount(Long postId) {
        validatePostExists(postId);
        int count = getPostLikeCount(postId);

        return new LikeCountServiceResponse(count);
    }

    public GetUsersLikedServiceResponse getLikedUsers(Long postId) {
        validatePostExists(postId);
        List<User> users = getPostLikeUserList(postId);

        return GetUsersLikedServiceResponse.from(users);
    }

    private void validatePostExists(Long postId) {
        if (!postRepository.existsByIdAndDeletedFalse(postId)) {
            throw new PostException(POST_NOT_FOUND);
        }
    }

    private List<User> getPostLikeUserList(Long postId) {
        return postLikeRepository.findAllByPostId(postId).stream()
                   .map(PostLike::getUser)
                   .toList();
    }

    private int getPostLikeCount(Long postId) {
        return postLikeRepository.countByPostId(postId);
    }
}
