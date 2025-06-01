package org.sopt.post.presentation.mapper;

import org.sopt.post.application.request.CreatePostServiceRequest;
import org.sopt.post.presentation.request.CreatePostRequest;

public class PostRequestMapper {

    public static CreatePostServiceRequest toCreatePostServiceRequest(CreatePostRequest createPostRequest) {
        return CreatePostServiceRequest.builder()
                   .title(createPostRequest.getTitle())
                   .content(createPostRequest.getContent())
                   .tag(createPostRequest.getTag())
                   .build();
    }
}
