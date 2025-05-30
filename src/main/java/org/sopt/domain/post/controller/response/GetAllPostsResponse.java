package org.sopt.domain.post.controller.response;

import java.util.List;

public record GetAllPostsResponse(List<GetSimplePostResponse> results) {
}
