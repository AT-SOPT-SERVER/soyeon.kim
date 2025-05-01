package org.sopt.domain.post.dto.response;

import java.util.List;

public record SearchResultResponse(List<SearchPostResponse> results) {
}
