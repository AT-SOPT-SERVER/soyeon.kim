package org.sopt.domain.post.dto.response;

import org.sopt.domain.post.domain.Post;
import org.sopt.domain.user.dto.response.GetUserResponse;

public record GetSimplePostResponse(Long id, GetUserResponse author, String title) {
    public static GetSimplePostResponse from(Post post) {
        return new GetSimplePostResponse(
                post.getId(),
                GetUserResponse.from(post.getUser()),
                post.getTitle()
        );
    }
}
