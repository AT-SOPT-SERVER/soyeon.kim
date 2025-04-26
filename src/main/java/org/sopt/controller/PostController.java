package org.sopt.controller;

import java.net.URI;
import java.util.List;
import org.sopt.common.response.ApiResponse;
import org.sopt.dto.PostRequest;
import org.sopt.dto.PostResponse;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<ApiResponse<Void>> createPost(@RequestBody final PostRequest postRequest) {
        Long createdId = postService.createPost(postRequest);
        URI location = URI.create("/posts/" + createdId);

        return ResponseEntity.created(location).body(ApiResponse.created());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPosts(@RequestParam(required = false) String search) {
        if (search == null || search.isBlank()) {
            return ResponseEntity.ok(ApiResponse.ok("✅ 성공적으로 전체 게시물을 조회했습니다.", postService.getAllPosts()));
        }

        return ResponseEntity.ok(
                ApiResponse.ok("✅ 성공적으로 게시물을 검색했습니다.", postService.searchPostsByKeyword(search))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("✅ 성공적으로 게시물을 조회했습니다.", postService.getPostById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);

        return ResponseEntity.ok(ApiResponse.ok("✅ 성공적으로 게시물을 삭제했습니다.", null));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePostTitle(@PathVariable Long id,
                                                             @RequestBody PostRequest postRequest) {
        postService.updatePostTitle(id, postRequest);

        return ResponseEntity.ok(ApiResponse.ok("✅ 성공적으로 게시물을 수정했습니다.", null));
    }
}
