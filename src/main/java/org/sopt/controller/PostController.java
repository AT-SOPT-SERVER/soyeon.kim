package org.sopt.controller;

import java.net.URI;
import org.sopt.common.response.ApiResponse;
import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/posts")
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Void>> createPost(@RequestBody final PostRequest postRequest) {
        Long createdId = postService.createPost(postRequest);
        URI location = URI.create("/posts/" + createdId);
        return ResponseEntity.created(location).body(ApiResponse.created());
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
    }

    @PatchMapping("/{id}")
    public void updatePostTitle(@PathVariable Long id, @RequestBody PostRequest postRequest) {
        try {
            postService.updatePostTitle(id, postRequest.getTitle());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }

    public void searchPostsByKeyword(String keyword) {
        postService.searchPostsByKeyword(keyword);
    }
}
