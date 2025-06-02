package org.sopt.comment.application.dto.response;

import java.util.List;

public record GetAllCommentsServiceResponse(List<GetCommentServiceResponse> comment) {
}
