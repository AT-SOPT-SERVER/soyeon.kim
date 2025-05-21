package org.sopt.domain.post.validator;

public interface PostValidationRule {
    void validate(Long userId, String title);
}
