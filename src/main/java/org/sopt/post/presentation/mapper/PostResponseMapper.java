package org.sopt.post.presentation.mapper;

import org.sopt.post.application.dto.response.GetAllPostsServiceResponse;
import org.sopt.post.application.dto.response.GetSimplePostServiceResponse;
import org.sopt.post.application.dto.response.SearchPostServiceResponse;
import org.sopt.post.application.dto.response.SearchResultServiceResponse;
import org.sopt.post.presentation.dto.response.AuthorResponse;
import org.sopt.post.presentation.dto.response.GetAllPostsResponse;
import org.sopt.post.presentation.dto.response.GetSimplePostResponse;
import org.sopt.post.presentation.dto.response.SearchPostResponse;
import org.sopt.post.presentation.dto.response.SearchResultResponse;

public class PostResponseMapper {

    public static GetAllPostsResponse toGetAllPostsResponse(GetAllPostsServiceResponse getAllPostsServiceResponse) {
        return new GetAllPostsResponse(
            getAllPostsServiceResponse.results().stream()
                .map(PostResponseMapper::toGetSimplePostResponse)
                .toList()
        );
    }

    public static SearchResultResponse toSearchResultResponse(SearchResultServiceResponse searchResultServiceResponse) {
        return new SearchResultResponse(
            searchResultServiceResponse.results().stream()
                .map(PostResponseMapper::toSearchPostResponse)
                .toList()
        );
    }

    private static GetSimplePostResponse toGetSimplePostResponse(
        GetSimplePostServiceResponse getSimplePostserviceResponse
    ) {
        return new GetSimplePostResponse(
            getSimplePostserviceResponse.id(),
            AuthorResponse.from(getSimplePostserviceResponse.authorId(), getSimplePostserviceResponse.authorName()),
            getSimplePostserviceResponse.title()
        );
    }

    private static SearchPostResponse toSearchPostResponse(SearchPostServiceResponse searchPostServiceResponse) {
        return new SearchPostResponse(
            searchPostServiceResponse.id(),
            AuthorResponse.from(searchPostServiceResponse.authorId(), searchPostServiceResponse.authorName()),
            searchPostServiceResponse.title()
        );
    }

}

