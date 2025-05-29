package org.sopt.domain.post.presentation.response;

import java.util.List;

public record GetAllPostsResponse(List<GetSimplePostResponse> results) {
}
