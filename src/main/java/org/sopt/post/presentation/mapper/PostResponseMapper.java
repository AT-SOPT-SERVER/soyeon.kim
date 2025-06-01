package org.sopt.post.presentation.mapper;

import org.sopt.post.application.dto.response.GetAllPostsServiceResponse;
import org.sopt.post.application.dto.response.GetSimplePostServiceResponse;
import org.sopt.post.presentation.dto.response.AuthorResponse;
import org.sopt.post.presentation.dto.response.GetAllPostsResponse;
import org.sopt.post.presentation.dto.response.GetSimplePostResponse;

public class PostResponseMapper {

    public static GetAllPostsResponse toGetAllPostsResponse(GetAllPostsServiceResponse getAllPostsServiceResponse) {
        return new GetAllPostsResponse(
            getAllPostsServiceResponse.results().stream()
                .map(PostResponseMapper::toGetSimplePostResponse)
                .toList()
        );
    }

    public static GetSimplePostResponse toGetSimplePostResponse(
        GetSimplePostServiceResponse getSimplePostserviceResponse
    ) {
        return new GetSimplePostResponse(
            getSimplePostserviceResponse.id(),
            AuthorResponse.from(getSimplePostserviceResponse.authorId(), getSimplePostserviceResponse.authorName()),
            getSimplePostserviceResponse.title()
        );
    }

}

