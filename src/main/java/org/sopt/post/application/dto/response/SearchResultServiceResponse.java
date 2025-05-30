package org.sopt.post.application.dto.response;

import java.util.List;

public record SearchResultServiceResponse(List<SearchPostServiceResponse> results) {
}
