package org.sopt.domain.post.presentation.mapper;

import org.sopt.domain.post.application.request.CreatePostServiceRequest;
import org.sopt.domain.post.presentation.request.CreatePostRequest;

public class PostRequestMapper {

    public static CreatePostServiceRequest toCreatePostServiceRequest(CreatePostRequest createPostRequest) {
        return CreatePostServiceRequest.builder()
                   .title(createPostRequest.getTitle())
                   .content(createPostRequest.getContent())
                   .tag(createPostRequest.getTag())
                   .build();
    }
}
