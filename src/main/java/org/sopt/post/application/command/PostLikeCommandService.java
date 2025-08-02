package org.sopt.post.application.command;

import static org.sopt.post.domain.exception.PostErrorCode.POST_NOT_FOUND;
import static org.sopt.post.domain.exception.PostLikeErrorCode.POST_ALREADY_LIKED;
import static org.sopt.post.domain.exception.PostLikeErrorCode.POST_LIKE_NOT_FOUND;
import static org.sopt.user.domain.exception.UserErrorCode.USER_NOT_FOUND;

import lombok.RequiredArgsConstructor;
import org.sopt.global.error.BusinessException;
import org.sopt.post.domain.Post;
import org.sopt.post.domain.PostLike;
import org.sopt.post.domain.exception.PostException;
import org.sopt.post.domain.exception.PostLikeException;
import org.sopt.post.infrastructure.repository.PostLikeRepository;
import org.sopt.post.infrastructure.repository.PostRepository;
import org.sopt.user.domain.User;
import org.sopt.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostLikeCommandService {

    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createPostLike(Long userId, Long postId) {
        validateLikeAvailable(userId, postId);
        User user = getUser(userId);
        Post post = getPost(postId);

        PostLike postLike = PostLike.create(user, post);
        postLikeRepository.save(postLike);
    }

    @Transactional
    public void dislikePost(Long userId, Long postId) {
        PostLike postLike = validateDislikeAvailable(userId, postId);
        postLikeRepository.delete(postLike);
    }

    private void validateLikeAvailable(Long userId, Long postId) {
        if (postLikeRepository.existsPostLikeByUserIdAndPostId(userId, postId)) {
            throw new PostLikeException(POST_ALREADY_LIKED);
        }
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                   .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
    }

    private Post getPost(Long postId) {
        return postRepository.findByIdAndDeletedFalse(postId)
                   .orElseThrow(() -> new PostException(POST_NOT_FOUND));
    }

    private PostLike validateDislikeAvailable(Long userId, Long postId) {
        return postLikeRepository.findByUserIdAndPostId(userId, postId)
                   .orElseThrow(() -> new PostLikeException(POST_LIKE_NOT_FOUND));
    }
}
