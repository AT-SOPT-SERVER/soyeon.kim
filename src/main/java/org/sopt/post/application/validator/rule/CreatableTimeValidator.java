package org.sopt.post.application.validator.rule;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import org.sopt.post.application.validator.PostValidationRule;
import org.sopt.global.error.BusinessException;
import org.sopt.post.domain.Post;
import org.sopt.post.application.exception.PostErrorCode;
import org.sopt.post.infrastructure.repository.PostRepository;

public class CreatableTimeValidator implements PostValidationRule {

    private final PostRepository postRepository;

    public CreatableTimeValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void validate(Long userId, String title) {
        Optional<Post> lastPost = postRepository.findFirstByUser_IdAndDeletedFalseOrderByCreatedAtDesc(userId);
        if (lastPost.isPresent()
                && Duration.between(lastPost.get().getCreatedAt(), LocalDateTime.now()).toMinutes() < 3) {
            throw new BusinessException(PostErrorCode.INVALID_CREATE_TIME);
        }
    }
}
