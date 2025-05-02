package org.sopt.domain.post.dto.response;

import java.util.List;

public record GetAllPostsResponse(List<GetSimplePostResponse> results) {
}
