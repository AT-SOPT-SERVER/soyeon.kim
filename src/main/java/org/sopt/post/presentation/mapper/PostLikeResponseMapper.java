package org.sopt.post.presentation.mapper;

import org.sopt.post.application.dto.response.GetUserLikedPostServiceResponse;
import org.sopt.post.application.dto.response.GetUsersLikedPostServiceResponse;
import org.sopt.post.application.dto.response.LikeCountServiceResponse;
import org.sopt.post.presentation.dto.response.GetUserLikedPostResponse;
import org.sopt.post.presentation.dto.response.GetUsersLikedResponse;
import org.sopt.post.presentation.dto.response.LikeCountResponse;

public class PostLikeResponseMapper {

    public static GetUsersLikedResponse toGetUsersLikedResponse(
        GetUsersLikedPostServiceResponse getUsersLikedPostServiceResponse
    ) {
        return new GetUsersLikedResponse(
            getUsersLikedPostServiceResponse.count(),
            getUsersLikedPostServiceResponse.users().stream()
                .map(PostLikeResponseMapper::toGetUserLikedPostResponse)
                .toList()
        );
    }

    public static LikeCountResponse toLikeCountResponse(
        LikeCountServiceResponse likeCountServiceResponse
    ) {
        return new LikeCountResponse(likeCountServiceResponse.count());
    }

    private static GetUserLikedPostResponse toGetUserLikedPostResponse(
        GetUserLikedPostServiceResponse getUserLikedPostServiceResponse
    ) {
        return new GetUserLikedPostResponse(
            getUserLikedPostServiceResponse.id(),
            getUserLikedPostServiceResponse.name()
        );
    }
}
