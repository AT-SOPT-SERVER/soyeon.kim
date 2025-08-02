package org.sopt.post.presentation;

import static org.sopt.post.presentation.mapper.PostRequestMapper.toCreatePostServiceRequest;
import static org.sopt.post.presentation.mapper.PostRequestMapper.toUpdatePostServiceRequest;
import static org.sopt.post.presentation.mapper.PostResponseMapper.toGetAllPostsResponse;
import static org.sopt.post.presentation.mapper.PostResponseMapper.toGetDetailedPostResponse;
import static org.sopt.post.presentation.message.PostMessage.CREATED_SUCCESS;
import static org.sopt.post.presentation.message.PostMessage.DELETED_SUCCESS;
import static org.sopt.post.presentation.message.PostMessage.RETRIEVED_ALL_SUCCESS;
import static org.sopt.post.presentation.message.PostMessage.RETRIEVED_SUCCESS;
import static org.sopt.post.presentation.message.PostMessage.SEARCHED_SUCCESS;
import static org.sopt.post.presentation.message.PostMessage.UPDATED_SUCCESS;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.post.application.command.PostCommandService;
import org.sopt.post.application.dto.request.CreatePostServiceRequest;
import org.sopt.post.application.dto.request.UpdatePostServiceRequest;
import org.sopt.post.application.dto.response.GetAllPostsServiceResponse;
import org.sopt.post.application.dto.response.GetDetailedPostServiceResponse;
import org.sopt.post.application.dto.response.SearchResultServiceResponse;
import org.sopt.post.application.query.PostQueryService;
import org.sopt.post.presentation.dto.request.CreatePostRequest;
import org.sopt.post.presentation.dto.request.UpdatePostRequest;
import org.sopt.post.presentation.dto.response.GetAllPostsResponse;
import org.sopt.post.presentation.dto.response.GetDetailedPostResponse;
import org.sopt.post.presentation.dto.response.SearchResultResponse;
import org.sopt.global.response.ApiResponse;

import org.sopt.post.presentation.mapper.PostResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createPost(
        @RequestHeader(required = false) Long userId,
        @Valid @RequestBody final CreatePostRequest createPostRequest
    ) {
        CreatePostServiceRequest serviceRequest = toCreatePostServiceRequest(createPostRequest);
        Long createdId = postCommandService.createPost(userId, serviceRequest);
        URI location = URI.create("/api/v1/posts/" + createdId);

        return ResponseEntity.created(location).body(ApiResponse.created(CREATED_SUCCESS));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<GetAllPostsResponse>> getAllPosts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        GetAllPostsServiceResponse serviceResponse = postQueryService.getAllPosts(page, size);
        GetAllPostsResponse response = toGetAllPostsResponse(serviceResponse);

        return ResponseEntity.ok(ApiResponse.ok(RETRIEVED_ALL_SUCCESS, response));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<SearchResultResponse>> searchPostsByKeyword(
        @RequestParam String keyword,
        @RequestParam String type
    ) {
        SearchResultServiceResponse serviceResponse = postQueryService.searchPostsByKeyword(keyword, type);
        SearchResultResponse response = PostResponseMapper.toSearchResultResponse(serviceResponse);

        return ResponseEntity.ok(ApiResponse.ok(SEARCHED_SUCCESS, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetDetailedPostResponse>> getPostById(@PathVariable Long id) {
        GetDetailedPostServiceResponse serviceResponse = postQueryService.getPostById(id);
        GetDetailedPostResponse response = toGetDetailedPostResponse(serviceResponse);

        return ResponseEntity.ok(ApiResponse.ok(RETRIEVED_SUCCESS, response));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePostTitle(
        @RequestHeader(required = false) Long userId,
        @PathVariable Long id,
        @Valid @RequestBody UpdatePostRequest updatePostRequest
    ) {
        UpdatePostServiceRequest updatePostServiceRequest = toUpdatePostServiceRequest(updatePostRequest);
        postCommandService.updatePostTitle(userId, id, updatePostServiceRequest);

        return ResponseEntity.ok(ApiResponse.ok(UPDATED_SUCCESS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePostById(
        @RequestHeader(required = false) Long userId,
        @PathVariable Long id
    ) {
        postCommandService.deletePostById(userId, id);

        return ResponseEntity.ok(ApiResponse.ok(DELETED_SUCCESS));
    }
}
