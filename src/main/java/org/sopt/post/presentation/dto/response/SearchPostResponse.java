package org.sopt.post.presentation.dto.response;

public record SearchPostResponse(
    Long id,
    AuthorResponse author,
    String title
) {
}
