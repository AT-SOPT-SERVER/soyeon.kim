package org.sopt.post.presentation.mapper;

import org.sopt.post.application.dto.request.CreatePostServiceRequest;
import org.sopt.post.application.dto.request.UpdatePostServiceRequest;
import org.sopt.post.presentation.dto.request.CreatePostRequest;
import org.sopt.post.presentation.dto.request.UpdatePostRequest;

public class PostRequestMapper {

    public static CreatePostServiceRequest toCreatePostServiceRequest(CreatePostRequest createPostRequest) {
        return CreatePostServiceRequest.builder()
                   .title(createPostRequest.getTitle())
                   .content(createPostRequest.getContent())
                   .tag(createPostRequest.getTag())
                   .build();
    }

    public static UpdatePostServiceRequest toUpdatePostServiceRequest(UpdatePostRequest updatePostRequest) {
        return UpdatePostServiceRequest.builder()
                   .title(updatePostRequest.getTitle())
                   .build();
    }

}
