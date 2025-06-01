package org.sopt.post.presentation.response;

import org.sopt.post.domain.Post;
import org.sopt.user.presentation.dto.response.GetUserResponse;

public record SearchPostResponse(Long id, GetUserResponse author, String title) {
    public static SearchPostResponse from(Post post) {
        return new SearchPostResponse(
                post.getId(),
                GetUserResponse.from(post.getUser()),
                post.getTitle()
        );
    }
}
