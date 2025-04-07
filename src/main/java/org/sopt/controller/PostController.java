package org.sopt.controller;

import java.util.List;
import org.sopt.domain.Post;
import org.sopt.service.PostService;

public class PostController {
    private final PostService postService = new PostService();

    public void createPost(final String title) {
        postService.createPost(title);
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
        return postService.updatePostTitle(id, title);
    }
}
