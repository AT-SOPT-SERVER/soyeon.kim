package org.sopt.post.application.dto.response;

import org.sopt.post.domain.Post;

public record GetSimplePostServiceResponse(
    Long id,
    Long authorId,
    String authorName,
    String title
) {

    public static GetSimplePostServiceResponse from(Post post) {
        return new GetSimplePostServiceResponse(
            post.getId(),
            post.getUser().getId(),
            post.getUser().getName(),
            post.getTitle()
        );
    }
}
