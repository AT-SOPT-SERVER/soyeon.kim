package org.sopt.post.presentation.dto.response;

public record GetSimplePostResponse(
    Long id,
    AuthorResponse author,
    String title
) {
}
