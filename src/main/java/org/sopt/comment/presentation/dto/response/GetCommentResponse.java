package org.sopt.comment.presentation.dto.response;

import java.time.LocalDateTime;

public record GetCommentResponse(
    Long id,
    CommentAuthorResponse author,
    String content,
    LocalDateTime createdAt,
    boolean isUpdated
) {
}
