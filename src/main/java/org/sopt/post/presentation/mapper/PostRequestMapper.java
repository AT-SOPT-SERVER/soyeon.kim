package org.sopt.post.presentation.mapper;

import org.sopt.post.application.dto.request.CreatePostServiceRequest;
import org.sopt.post.application.dto.request.UpdatePostServiceRequest;
import org.sopt.post.presentation.dto.request.CreatePostRequest;
import org.sopt.post.presentation.dto.request.UpdatePostRequest;

public class PostRequestMapper {

    public static CreatePostServiceRequest toCreatePostServiceRequest(CreatePostRequest createPostRequest) {
        return new CreatePostServiceRequest(
            createPostRequest.getTitle(),
            createPostRequest.getContent(),
            createPostRequest.getTag()
        );
    }

    public static UpdatePostServiceRequest toUpdatePostServiceRequest(UpdatePostRequest updatePostRequest) {
        return new UpdatePostServiceRequest(
            updatePostRequest.getTitle()
        );
    }
}
