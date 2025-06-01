package org.sopt.post.presentation.dto.response;

public record GetDetailedPostResponse(Long id, AuthorResponse user, String title, String content) {
}
