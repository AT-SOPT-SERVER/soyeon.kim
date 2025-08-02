package org.sopt.post.presentation.dto.response;

public record AuthorResponse(Long id, String name) {

    public static AuthorResponse from(Long id, String name) {
        return new AuthorResponse(id, name);
    }
}
