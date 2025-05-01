package org.sopt.domain.post.controller;

import java.net.URI;
import java.util.List;
import org.sopt.domain.post.dto.request.CreatePostRequest;
import org.sopt.domain.post.dto.request.UpdatePostRequest;
import org.sopt.domain.post.dto.response.GetDetailedPostResponse;
import org.sopt.domain.post.dto.response.GetPostsResponse;
import org.sopt.global.common.response.ApiResponse;
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

@RequestMapping("/posts")
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createPost(
            @RequestHeader Long userId,
            @RequestBody final CreatePostRequest createPostRequest) {
        Long createdId = postService.createPost(userId, createPostRequest);
        URI location = URI.create("/posts/" + createdId);

        return ResponseEntity.created(location).body(ApiResponse.created());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetPostsResponse>>> getAllPosts(
            @RequestParam(required = false) String search) {
        if (search == null || search.isBlank()) {
            return ResponseEntity.ok(ApiResponse.ok("✅ 성공적으로 전체 게시물을 조회했습니다.", postService.getAllPosts()));
        }

        return ResponseEntity.ok(
                ApiResponse.ok("✅ 성공적으로 게시물을 검색했습니다.", postService.searchPostsByKeyword(search))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetDetailedPostResponse>> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("✅ 성공적으로 게시물을 조회했습니다.", postService.getPostById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);

        return ResponseEntity.ok(ApiResponse.ok("✅ 성공적으로 게시물을 삭제했습니다.", null));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePostTitle(@PathVariable Long id,
                                                             @RequestBody UpdatePostRequest updatePostRequest) {
        postService.updatePostTitle(id, updatePostRequest);

        return ResponseEntity.ok(ApiResponse.ok("✅ 성공적으로 게시물을 수정했습니다.", null));
    }
}
