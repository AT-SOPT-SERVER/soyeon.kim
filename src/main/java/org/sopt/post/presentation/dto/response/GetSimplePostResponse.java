package org.sopt.post.presentation.dto.response;

import org.sopt.post.domain.Post;
import org.sopt.user.presentation.dto.response.GetUserResponse;

public record GetSimplePostResponse(Long id, GetUserResponse author, String title) {

    public static GetSimplePostResponse from(Post post) {
        return new GetSimplePostResponse(
                post.getId(),
                GetUserResponse.from(post.getUser()),
                post.getTitle()
        );
    }

}
