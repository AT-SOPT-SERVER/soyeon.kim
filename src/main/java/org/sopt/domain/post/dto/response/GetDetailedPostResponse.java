package org.sopt.domain.post.dto.response;

import org.sopt.domain.post.domain.Post;
import org.sopt.domain.user.dto.response.GetUserResponse;

public record GetDetailedPostResponse(Long id, GetUserResponse user, String title, String content) {
    public static GetDetailedPostResponse from(Post post) {
        return new GetDetailedPostResponse(
                post.getId(),
                GetUserResponse.from(post.getUser()),
                post.getTitle(),
                post.getContent()
        );
    }
}
