package org.sopt.post.application.dto.response;

import org.sopt.post.domain.Post;

public record GetDetailedPostServiceResponse(
    Long id,
    Long authorId,
    String authorName,
    String title,
    String content
) {

    public static GetDetailedPostServiceResponse from(Post post) {
        return new GetDetailedPostServiceResponse(
            post.getId(),
            post.getUser().getId(),
            post.getUser().getName(),
            post.getTitle(),
            post.getContent()
        );
    }
}
