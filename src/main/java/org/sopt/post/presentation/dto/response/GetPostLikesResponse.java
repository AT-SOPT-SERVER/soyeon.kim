package org.sopt.post.presentation.dto.response;

import java.util.List;

public record GetPostLikesResponse(
    int count,
    List<GetUserLikedPostResponse> users
) {
}
