package org.sopt.domain.post.dto.response;

import org.sopt.domain.post.domain.Post;
import org.sopt.domain.user.dto.response.GetUserResponse;

public record SearchPostResponse(Long id, GetUserResponse author, String title) {
    public static SearchPostResponse from(Post post) {
        return new SearchPostResponse(
                post.getId(),
                GetUserResponse.from(post.getUser()),
                post.getTitle()
        );
    }
}
