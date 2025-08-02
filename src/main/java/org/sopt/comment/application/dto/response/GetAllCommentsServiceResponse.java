package org.sopt.comment.application.dto.response;

import java.util.List;
import org.springframework.data.domain.Page;

public record GetAllCommentsServiceResponse(
    List<GetCommentServiceResponse> comments,
    int page,
    int size,
    int totalPages,
    long totalElements
) {

    public static GetAllCommentsServiceResponse from(
        List<GetCommentServiceResponse> comments,
        Page<GetCommentServiceResponse> pageData
    ) {
        return new GetAllCommentsServiceResponse(
            comments,
            pageData.getNumber(),
            pageData.getSize(),
            pageData.getTotalPages(),
            pageData.getTotalElements()
        );
    }
}
