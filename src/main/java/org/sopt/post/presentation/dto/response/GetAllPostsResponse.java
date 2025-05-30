package org.sopt.post.presentation.dto.response;

import java.util.List;

public record GetAllPostsResponse(List<GetSimplePostResponse> results) {
}
