package org.sopt.comment.application.dto.response;

import java.time.LocalDateTime;
import org.sopt.comment.domain.Comment;

public record GetCommentServiceResponse(
    Long commentId,
    Long userId,
    String userName,
    String content,
    LocalDateTime createdAt,
    boolean isUpdated
) {

    public static GetCommentServiceResponse from(Comment comment) {
        return new GetCommentServiceResponse(
            comment.getId(),
            comment.getUser().getId(),
            comment.getUser().getName(),
            comment.getContent(),
            comment.getCreatedAt(),
            isModified(comment)
        );
    }

    private static boolean isModified(Comment comment) {
        return comment.getUpdatedAt().isAfter(comment.getCreatedAt());
    }
}
