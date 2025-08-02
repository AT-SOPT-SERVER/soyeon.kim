package org.sopt.comment.presentation.dto.response;

import java.util.List;

public record GetAllCommentsResponse(
    List<GetCommentResponse> comments,
    int page,
    int size,
    int totalPages,
    long totalElements
) {
}
