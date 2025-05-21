package org.sopt.domain.post.validator;

import java.util.List;
import org.sopt.domain.post.repository.PostRepository;

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
