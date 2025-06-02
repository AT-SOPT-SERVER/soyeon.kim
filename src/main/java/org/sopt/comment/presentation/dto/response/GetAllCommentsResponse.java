package org.sopt.comment.presentation.dto.response;

import java.util.List;

public record GetAllCommentsResponse(List<GetCommentResponse> comments) {
}
