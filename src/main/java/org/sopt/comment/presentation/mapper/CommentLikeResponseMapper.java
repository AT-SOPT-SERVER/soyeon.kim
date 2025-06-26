package org.sopt.comment.presentation.mapper;

import org.sopt.comment.application.dto.response.GetCommentLikesCountServiceResponse;
import org.sopt.comment.presentation.dto.response.GetCommentLikesCountResponse;

public class CommentLikeResponseMapper {

    public static GetCommentLikesCountResponse toGetCommentLikesCountResponse(
        GetCommentLikesCountServiceResponse serviceResponse
    ) {
        return new GetCommentLikesCountResponse(serviceResponse.count());
    }
}
