package org.sopt.post.application.validator.rule;

import org.sopt.post.application.validator.PostValidationRule;
import org.sopt.global.error.BusinessException;
import org.sopt.post.domain.exception.PostErrorCode;
import org.sopt.post.infrastructure.repository.PostRepository;

public class DuplicateTitleValidator implements PostValidationRule {

    private final PostRepository postRepository;

    public DuplicateTitleValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void validate(Long userId, String title) {
        if (postRepository.findPostByTitleAndDeletedFalse(title).isPresent()) {
            throw new BusinessException(PostErrorCode.TITLE_DUPLICATED);
        }
    }
}
