package org.sopt.post.application.dto.response;

import org.sopt.post.domain.Post;

public record SearchPostServiceResponse(
    Long id,
    Long authorId,
    String authorName,
    String title
) {

    public static SearchPostServiceResponse from(Post post) {
        return new SearchPostServiceResponse(
            post.getId(),
            post.getUser().getId(),
            post.getUser().getName(),
            post.getTitle()
        );
    }
}
