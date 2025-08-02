package org.sopt.post.application.dto.response;

import java.util.List;
import org.sopt.user.domain.User;

public record GetUsersLikedServiceResponse(
    List<GetUserLikedPostServiceResponse> users
) {

    public static GetUsersLikedServiceResponse from(List<User> users) {
        return new GetUsersLikedServiceResponse(users.stream()
                                                    .map(GetUserLikedPostServiceResponse::from)
                                                    .toList());
    }
}
