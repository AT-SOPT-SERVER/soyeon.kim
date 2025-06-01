package org.sopt.post.presentation.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import org.sopt.post.domain.Post;

public record SearchResultResponse(List<SearchPostResponse> results) {
    public static SearchResultResponse from(List<Post> posts) {
        return new SearchResultResponse(posts.stream()
                .map(SearchPostResponse::from)
                .collect(Collectors.toList()));
    }
}
