package org.sopt.domain.post.controller;

import org.sopt.domain.post.dto.request.CreatePostRequest;
import org.sopt.domain.post.dto.request.UpdatePostRequest;
import org.sopt.domain.post.dto.response.GetAllPostsResponse;
import org.sopt.domain.post.dto.response.GetDetailedPostResponse;
import org.sopt.domain.post.dto.response.SearchResultResponse;
import org.sopt.global.response.ApiResponse;
import org.sopt.domain.post.service.PostService;

import org.springframework.http.HttpStatus;
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

@RequestMapping("/posts")
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ApiResponse<Void> createPost(
        @RequestHeader(required = false) Long userId,
        @RequestBody final CreatePostRequest createPostRequest) {
        Long createdId = postService.createPost(userId, createPostRequest);

        return ApiResponse.created("성공적으로 " + createdId + "번 게시글을 저장했습니다.");
    }

    @GetMapping
    public ApiResponse<GetAllPostsResponse> getAllPosts() {
        return ApiResponse.ok("성공적으로 전체 게시물을 조회했습니다.", postService.getAllPosts());
    }

    @GetMapping("/search")
    public ApiResponse<SearchResultResponse> searchPostsByKeyword(
        @RequestParam String keyword,
        @RequestParam String type
    ) {
        return ApiResponse.ok("성공적으로 게시물을 검색했습니다.", postService.searchPostsByKeyword(keyword, type));
    }

    @GetMapping("/{id}")
    public ApiResponse<GetDetailedPostResponse> getPostById(@PathVariable Long id) {
        return ApiResponse.ok("성공적으로 게시물을 조회했습니다.", postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePostById(
        @RequestHeader(required = false) Long userId,
        @PathVariable Long id
    ) {
        postService.deletePostById(userId, id);

        return ApiResponse.ok("성공적으로 게시물을 삭제했습니다.");
    }

    @PatchMapping("/{id}")
    public ApiResponse<Void> updatePostTitle(
        @RequestHeader(required = false) Long userId,
        @PathVariable Long id,
        @RequestBody UpdatePostRequest updatePostRequest
    ) {
        postService.updatePostTitle(userId, id, updatePostRequest);

        return ApiResponse.ok("성공적으로 게시물을 수정했습니다.");
    }
}
