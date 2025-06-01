package org.sopt.comment.presentation.mapper;

import org.sopt.comment.application.dto.request.CreateCommentServiceRequest;
import org.sopt.comment.presentation.dto.request.CreateCommentRequest;

public class CommentRequestMapper {

    public static CreateCommentServiceRequest toCreateCommentServiceRequest(
        Long userId,
        Long postId,
        CreateCommentRequest createCommentRequest
    ) {
        return CreateCommentServiceRequest.of(userId, postId, createCommentRequest.content());
    }
}
