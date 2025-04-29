package org.sopt.domain.post.dto.response;

import org.sopt.domain.post.domain.Post;

public record PostResponse(Long id, String title) {
    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getTitle());
    }
}
