package org.sopt.domain.user.dto.response;

import org.sopt.domain.user.domain.User;

public record GetUserResponse(Long id, String name) {
    public static GetUserResponse from(User user) {
        return new GetUserResponse(user.getId(), user.getName());
    }
}
