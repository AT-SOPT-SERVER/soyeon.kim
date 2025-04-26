package org.sopt.validator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import org.sopt.common.exception.BusinessException;
import org.sopt.domain.Post;
import org.sopt.exception.PostErrorCode;
import org.sopt.repository.PostRepository;

public class CreatableTimeValidator implements PostValidationRule {
    private final PostRepository postRepository;

    public CreatableTimeValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void validate(String title) {
        Optional<Post> lastPost = postRepository.findFirstByOrderByCreatedAtDesc();
        if (lastPost.isPresent()
                && Duration.between(lastPost.get().getCreatedAt(), LocalDateTime.now()).toMinutes() < 3) {
            throw new BusinessException(PostErrorCode.INVALID_CREATE_TIME);
        }
    }
}
