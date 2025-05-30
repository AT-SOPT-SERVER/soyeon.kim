package org.sopt.domain.post.controller;

import static org.sopt.domain.post.message.PostMessage.CREATED_SUCCESS;
import static org.sopt.domain.post.message.PostMessage.DELETED_SUCCESS;
import static org.sopt.domain.post.message.PostMessage.RETRIEVED_ALL_SUCCESS;
import static org.sopt.domain.post.message.PostMessage.RETRIEVED_SUCCESS;
import static org.sopt.domain.post.message.PostMessage.SEARCHED_SUCCESS;
import static org.sopt.domain.post.message.PostMessage.UPDATED_SUCCESS;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.post.dto.request.CreatePostRequest;
import org.sopt.domain.post.dto.request.UpdatePostRequest;
import org.sopt.domain.post.dto.response.GetAllPostsResponse;
import org.sopt.domain.post.dto.response.GetDetailedPostResponse;
import org.sopt.domain.post.dto.response.SearchResultResponse;
import org.sopt.global.response.ApiResponse;
import org.sopt.domain.post.service.PostService;

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
        @RequestBody final CreatePostRequest createPostRequest
    ) {
        Long createdId = postService.createPost(userId, createPostRequest);
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
        @RequestBody UpdatePostRequest updatePostRequest
    ) {
        postService.updatePostTitle(userId, id, updatePostRequest);

        return ResponseEntity.ok(ApiResponse.ok(UPDATED_SUCCESS));
    }
}
