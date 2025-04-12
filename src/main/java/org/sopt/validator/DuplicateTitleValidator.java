package org.sopt.validator;

import org.sopt.repository.PostRepository;

public class DuplicateTitleValidator implements PostValidationRule {
    private final PostRepository postRepository;

    public DuplicateTitleValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void validate(String title) {
        if (postRepository.existsByTitle(title)) {
            throw new IllegalArgumentException("⚠️ 게시물 제목은 중복될 수 없습니다!");
        }
    }
}
