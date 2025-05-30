package org.sopt.domain.post.application.validator.rule;

import org.sopt.domain.post.application.validator.PostValidationRule;
import org.sopt.global.error.BusinessException;
import org.sopt.domain.post.presentation.exception.PostErrorCode;
import org.sopt.domain.post.infrastructure.repository.PostRepository;

public class DuplicateTitleValidator implements PostValidationRule {
    private final PostRepository postRepository;

    public DuplicateTitleValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void validate(Long userId, String title) {
        if (postRepository.findPostByTitle(title).isPresent()) {
            throw new BusinessException(PostErrorCode.TITLE_DUPLICATED);
        }
    }
}
