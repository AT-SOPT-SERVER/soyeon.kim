package org.sopt.user.dto.response;

import org.sopt.user.domain.User;

public record GetUserResponse(Long id, String name) {
    public static GetUserResponse from(User user) {
        return new GetUserResponse(user.getId(), user.getName());
    }
}
