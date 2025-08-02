package org.sopt.post.presentation.dto.response;

import java.util.List;

public record SearchResultResponse(List<SearchPostResponse> results) {
}
