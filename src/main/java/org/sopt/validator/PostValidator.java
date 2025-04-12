package org.sopt.validator;

import java.util.List;
import org.sopt.repository.PostRepository;

public class PostValidator {
    private final List<PostValidationRule> rules;

    public PostValidator(PostRepository postRepository) {
        this.rules = List.of(
                new DuplicateTitleValidator(postRepository),
                new CreatableTimeValidator(postRepository)
        );
    }

    public void validateAll(String title) {
        for (PostValidationRule rule : rules) {
            rule.validate(title);
        }
    }
}
