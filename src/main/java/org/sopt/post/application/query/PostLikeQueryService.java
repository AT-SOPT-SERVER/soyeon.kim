package org.sopt.post.application.query;

import static org.sopt.post.application.exception.PostErrorCode.POST_NOT_FOUND;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.global.error.BusinessException;
import org.sopt.post.application.dto.response.GetUsersLikedPostServiceResponse;
import org.sopt.post.domain.PostLike;
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

    public GetUsersLikedPostServiceResponse getUsersLikedPost(Long postId) {
        validatePostExists(postId);

        int count = postLikeRepository.countByPostId(postId);
        List<User> users = postLikeRepository.findAllByPostId(postId).stream()
                               .map(PostLike::getUser)
                               .toList();

        return GetUsersLikedPostServiceResponse.from(count, users);
    }

    private void validatePostExists(Long postId) {
        if (!postRepository.existsByIdAndDeletedFalse(postId)) {
            throw new BusinessException(POST_NOT_FOUND);
        }
    }
}
