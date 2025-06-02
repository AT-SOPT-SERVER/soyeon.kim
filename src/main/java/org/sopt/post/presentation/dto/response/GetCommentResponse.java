package org.sopt.post.presentation.dto.response;

import java.time.LocalDateTime;
import org.sopt.post.application.dto.response.GetCommentServiceResponse;

public record GetCommentResponse(
    Long id,
    CommentAuthorResponse author,
    String content,
    LocalDateTime createdAt,
    boolean isModified
) {
}
