package org.sopt.validator;

import org.sopt.common.exception.BusinessException;
import org.sopt.exception.PostErrorCode;
import org.sopt.repository.PostRepository;

public class DuplicateTitleValidator implements PostValidationRule {
    private final PostRepository postRepository;

    public DuplicateTitleValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void validate(String title) {
        if (postRepository.findPostByTitle(title).isPresent()) {
            throw new BusinessException(PostErrorCode.TITLE_DUPLICATED);
        }
    }
}
