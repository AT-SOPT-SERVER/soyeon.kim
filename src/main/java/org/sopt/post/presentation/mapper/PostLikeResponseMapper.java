package org.sopt.post.presentation.mapper;

import org.sopt.post.application.dto.response.GetUserLikedPostServiceResponse;
import org.sopt.post.application.dto.response.GetPostLikesServiceResponse;
import org.sopt.post.application.dto.response.GetUsersLikedServiceResponse;
import org.sopt.post.application.dto.response.LikeCountServiceResponse;
import org.sopt.post.presentation.dto.response.GetUserLikedPostResponse;
import org.sopt.post.presentation.dto.response.GetPostLikesResponse;
import org.sopt.post.presentation.dto.response.GetUsersLikedResponse;
import org.sopt.post.presentation.dto.response.LikeCountResponse;

public class PostLikeResponseMapper {

    public static GetPostLikesResponse toGetUsersLikedResponse(
        GetPostLikesServiceResponse getPostLikesServiceResponse
    ) {
        return new GetPostLikesResponse(
            getPostLikesServiceResponse.count(),
            getPostLikesServiceResponse.users().stream()
                .map(PostLikeResponseMapper::toGetUserLikedPostResponse)
                .toList()
        );
    }

    public static LikeCountResponse toLikeCountResponse(
        LikeCountServiceResponse likeCountServiceResponse
    ) {
        return new LikeCountResponse(likeCountServiceResponse.count());
    }

    public static GetUsersLikedResponse toGetUsersLikedResponse(
        GetUsersLikedServiceResponse getUsersLikedServiceResponse
    ) {
        return new GetUsersLikedResponse(getUsersLikedServiceResponse.users().stream()
                                             .map(PostLikeResponseMapper::toGetUserLikedPostResponse)
                                             .toList());
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
