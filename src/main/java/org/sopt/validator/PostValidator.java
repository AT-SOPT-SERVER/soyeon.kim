package org.sopt.validator;

import org.sopt.service.PostService;

public class PostValidator {
    private final PostService postService;

    public PostValidator(final PostService postService) {
        this.postService = postService;
    }

    public void isDuplicatedTitle(final String title) {
        boolean isDuplicated = postService.getAllPosts().stream()
                .anyMatch(post -> post.getTitle().equals(title));

        if(isDuplicated) {
            throw new IllegalArgumentException("⚠️ 게시물 제목은 중복될 수 없습니다!");
        }
    }
}
