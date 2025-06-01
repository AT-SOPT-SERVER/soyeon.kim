package org.sopt.comment.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCommentRequest(
    @NotBlank(message = "댓글은 비워둘 수 없습니다.") String content
) {
}
