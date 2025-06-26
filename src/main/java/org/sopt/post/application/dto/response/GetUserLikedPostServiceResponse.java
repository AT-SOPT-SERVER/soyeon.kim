package org.sopt.post.application.dto.response;

import org.sopt.user.domain.User;

public record GetUserLikedPostServiceResponse(
    Long id,
    String name
) {

    public static GetUserLikedPostServiceResponse from(User user) {
        return new GetUserLikedPostServiceResponse(user.getId(), user.getName());
    }
}
