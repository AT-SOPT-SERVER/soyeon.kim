package org.sopt.controller;

import java.util.List;
import org.sopt.domain.Post;
import org.sopt.service.PostService;

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public boolean createPost(String title) {
        try {
            return postService.createPost(title);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(int id) {
        return postService.getPostById(id);
    }

    public boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }

    public boolean updatePostTitle(int id, String title) {
        try {
            return postService.updatePostTitle(id, title);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }

    public boolean createFile() {
        try {
            return postService.createFile();
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
