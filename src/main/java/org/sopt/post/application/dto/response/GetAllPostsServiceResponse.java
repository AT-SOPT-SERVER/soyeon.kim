package org.sopt.post.application.dto.response;

import java.util.List;

public record GetAllPostsServiceResponse(
    List<GetSimplePostServiceResponse> results,
    int page,
    int size,
    int totalPages,
    long totalElements
) {
}
