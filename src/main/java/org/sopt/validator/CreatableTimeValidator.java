package org.sopt.validator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

public class CreatableTimeValidator implements PostValidationRule {
    private final PostRepository postRepository;

    public CreatableTimeValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void validate(String title) {
        Optional<Post> lastPost = postRepository.findLastPost();
        if (lastPost.isPresent()
                && Duration.between(lastPost.get().getCreatedAt(), LocalDateTime.now()).toSeconds() < 10) {
            throw new IllegalStateException("⚠️ 3분이 지나야 새 글을 작성할 수 있습니다.");
        }
    }
}
