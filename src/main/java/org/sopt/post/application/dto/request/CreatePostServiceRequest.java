package org.sopt.post.application.dto.request;

public record CreatePostServiceRequest(
    String title,
    String content,
    String tag
) {
}
