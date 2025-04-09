package org.sopt.validator;

import org.sopt.repository.PostRepository;

public class PostValidator {
    private final PostRepository postRepository;

    public PostValidator(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void isDuplicatedTitle(final String title) {
        boolean isDuplicated = postRepository.findAll().stream()
                .anyMatch(post -> post.getTitle().equals(title));

        if (isDuplicated) {
            throw new IllegalArgumentException("⚠️ 게시물 제목은 중복될 수 없습니다!");
        }
    }
}
