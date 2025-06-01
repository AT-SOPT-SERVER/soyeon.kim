package org.sopt.post.presentation;

import static org.sopt.post.presentation.message.PostMessage.CREATED_SUCCESS;
import static org.sopt.post.presentation.message.PostMessage.DELETED_SUCCESS;
import static org.sopt.post.presentation.message.PostMessage.RETRIEVED_ALL_SUCCESS;
import static org.sopt.post.presentation.message.PostMessage.RETRIEVED_SUCCESS;
import static org.sopt.post.presentation.message.PostMessage.SEARCHED_SUCCESS;
import static org.sopt.post.presentation.message.PostMessage.UPDATED_SUCCESS;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.post.application.request.CreatePostServiceRequest;
import org.sopt.post.presentation.mapper.PostRequestMapper;
import org.sopt.post.presentation.dto.request.CreatePostRequest;
import org.sopt.post.presentation.dto.request.UpdatePostRequest;
import org.sopt.post.presentation.dto.response.GetAllPostsResponse;
import org.sopt.post.presentation.dto.response.GetDetailedPostResponse;
import org.sopt.post.presentation.dto.response.SearchResultResponse;
import org.sopt.global.response.ApiResponse;
import org.sopt.post.application.PostService;

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

    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createPost(
        @RequestHeader(required = false) Long userId,
        @Valid @RequestBody final CreatePostRequest createPostRequest
    ) {
        CreatePostServiceRequest serviceRequest = PostRequestMapper.toCreatePostServiceRequest(createPostRequest);
        Long createdId = postService.createPost(userId, serviceRequest);
        URI location = URI.create("/api/v1/posts/" + createdId);

        return ResponseEntity.created(location).body(ApiResponse.created(CREATED_SUCCESS));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<GetAllPostsResponse>> getAllPosts() {
        return ResponseEntity.ok(ApiResponse.ok(RETRIEVED_ALL_SUCCESS, postService.getAllPosts()));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<SearchResultResponse>> searchPostsByKeyword(
        @RequestParam String keyword,
        @RequestParam String type
    ) {
        return ResponseEntity.ok(ApiResponse.ok(SEARCHED_SUCCESS, postService.searchPostsByKeyword(keyword, type)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetDetailedPostResponse>> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(RETRIEVED_SUCCESS, postService.getPostById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePostById(
        @RequestHeader(required = false) Long userId,
        @PathVariable Long id
    ) {
        postService.deletePostById(userId, id);

        return ResponseEntity.ok(ApiResponse.ok(DELETED_SUCCESS));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePostTitle(
        @RequestHeader(required = false) Long userId,
        @PathVariable Long id,
        @Valid @RequestBody UpdatePostRequest updatePostRequest
    ) {
        postService.updatePostTitle(userId, id, updatePostRequest);

        return ResponseEntity.ok(ApiResponse.ok(UPDATED_SUCCESS));
    }
}
