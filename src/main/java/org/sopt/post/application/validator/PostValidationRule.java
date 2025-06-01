package org.sopt.post.application.validator;

public interface PostValidationRule {

    void validate(Long userId, String title);
}
