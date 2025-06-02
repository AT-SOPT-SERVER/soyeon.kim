package org.sopt.post.application.dto.response;

import java.util.List;
import org.sopt.user.domain.User;

public record GetUsersLikedPostServiceResponse(
    int count,
    List<GetUserLikedPostServiceResponse> users
) {

    public static GetUsersLikedPostServiceResponse from(int count, List<User> users) {
        return new GetUsersLikedPostServiceResponse(
            count,
            users.stream().map(GetUserLikedPostServiceResponse::from)
                .toList()
        );
    }
}
