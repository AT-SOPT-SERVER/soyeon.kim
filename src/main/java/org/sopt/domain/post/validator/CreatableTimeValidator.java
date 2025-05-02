package org.sopt.domain.post.validator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import org.sopt.global.error.BusinessException;
import org.sopt.domain.post.domain.Post;
import org.sopt.domain.post.exception.PostErrorCode;
import org.sopt.domain.post.repository.PostRepository;

public class CreatableTimeValidator implements PostValidationRule {
    private final PostRepository postRepository;

    public CreatableTimeValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void validate(Long userId, String title) {
        Optional<Post> lastPost = postRepository.findFirstByUser_IdOrderByCreatedAtDesc(userId);
        if (lastPost.isPresent()
                && Duration.between(lastPost.get().getCreatedAt(), LocalDateTime.now()).toMinutes() < 3) {
            throw new BusinessException(PostErrorCode.INVALID_CREATE_TIME);
        }
    }
}
