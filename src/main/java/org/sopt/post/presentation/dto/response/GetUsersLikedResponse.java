package org.sopt.post.presentation.dto.response;

import java.util.List;

public record GetUsersLikedResponse(
    int count,
    List<GetUserLikedPostResponse> users
) {
}
