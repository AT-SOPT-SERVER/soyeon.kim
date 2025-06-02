package org.sopt.post.presentation.dto.response;

import java.util.List;

public record GetDetailedPostResponse(
    Long id,
    AuthorResponse user,
    String title,
    String content,
    List<GetCommentResponse> comments
) {
}
