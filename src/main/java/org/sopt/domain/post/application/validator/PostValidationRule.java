package org.sopt.domain.post.application.validator;

public interface PostValidationRule {
    void validate(Long userId, String title);
}
