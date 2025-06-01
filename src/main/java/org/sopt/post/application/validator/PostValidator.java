package org.sopt.post.application.validator;

import java.util.List;
import org.sopt.post.application.validator.rule.CreatableTimeValidator;
import org.sopt.post.application.validator.rule.DuplicateTitleValidator;
import org.sopt.post.infrastructure.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class PostValidator {
    private final List<PostValidationRule> rules;

    public PostValidator(PostRepository postRepository) {
        this.rules = List.of(
                new DuplicateTitleValidator(postRepository),
                new CreatableTimeValidator(postRepository)
        );
    }

    public void validateAll(Long userId, String title) {
        for (PostValidationRule rule : rules) {
            rule.validate(userId, title);
        }
    }
}
