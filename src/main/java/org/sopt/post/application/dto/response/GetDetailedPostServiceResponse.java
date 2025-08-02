package org.sopt.post.application.dto.response;

import java.util.List;
import org.sopt.post.domain.Post;

public record GetDetailedPostServiceResponse(
    Long id,
    Long authorId,
    String authorName,
    String title,
    String content,
    List<GetCommentServiceResponse> comments
) {

    public static GetDetailedPostServiceResponse from(Post post) {
        return new GetDetailedPostServiceResponse(
            post.getId(),
            post.getUser().getId(),
            post.getUser().getName(),
            post.getTitle(),
            post.getContent(),
            getCommentServiceResponses(post)
        );
    }

    private static List<GetCommentServiceResponse> getCommentServiceResponses(Post post) {
        return post.getActiveComments().stream()
                   .map(GetCommentServiceResponse::from)
                   .toList();
    }
}
