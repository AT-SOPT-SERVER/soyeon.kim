package org.sopt.comment.presentation.mapper;

import org.sopt.comment.application.dto.response.GetAllCommentsServiceResponse;
import org.sopt.comment.application.dto.response.GetCommentServiceResponse;
import org.sopt.comment.presentation.dto.response.CommentAuthorResponse;
import org.sopt.comment.presentation.dto.response.GetAllCommentsResponse;
import org.sopt.comment.presentation.dto.response.GetCommentResponse;

public class CommentResponseMapper {

    public static GetAllCommentsResponse toGetAllCommentsResponse(GetAllCommentsServiceResponse serviceResponse) {
        return new GetAllCommentsResponse(
            serviceResponse.comments().stream()
                .map(CommentResponseMapper::toGetCommentResponse)
                .toList(),
            serviceResponse.page(),
            serviceResponse.size(),
            serviceResponse.totalPages(),
            serviceResponse.totalElements()
        );
    }

    private static GetCommentResponse toGetCommentResponse(GetCommentServiceResponse serviceResponse) {
        return new GetCommentResponse(
            serviceResponse.commentId(),
            toCommentAuthorResponse(serviceResponse.userId(), serviceResponse.userName()),
            serviceResponse.content(),
            serviceResponse.createdAt(),
            serviceResponse.isUpdated()
        );
    }

    private static CommentAuthorResponse toCommentAuthorResponse(Long userId, String userName) {
        return new CommentAuthorResponse(userId, userName);
    }
}
