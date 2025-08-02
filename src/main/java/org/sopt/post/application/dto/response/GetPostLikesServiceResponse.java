package org.sopt.post.application.dto.response;

import java.util.List;
import org.sopt.user.domain.User;

public record GetPostLikesServiceResponse(
    int count,
    List<GetUserLikedPostServiceResponse> users
) {

    public static GetPostLikesServiceResponse from(int count, List<User> users) {
        return new GetPostLikesServiceResponse(
            count,
            users.stream().map(GetUserLikedPostServiceResponse::from)
                .toList()
        );
    }
}
