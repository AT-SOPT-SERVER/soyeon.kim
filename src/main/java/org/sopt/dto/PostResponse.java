package org.sopt.dto;

import org.sopt.domain.Post;

public record PostResponse(Long id, String title) {
    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getTitle());
    }
}
