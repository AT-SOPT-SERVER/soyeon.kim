package org.sopt.controller;

import java.util.List;
import org.sopt.domain.Post;
import org.sopt.service.PostService;
import org.sopt.validator.PostValidator;

public class PostController {
    private final PostService postService = new PostService();
    private final PostValidator postValidator = new PostValidator(postService);

    public void createPost(final String title) {
        postValidator.isDuplicatedTitle(title);
        postService.createPost(title);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(final int id) {
        return postService.getPostById(id);
    }

    public boolean deletePostById(final int id) {
        return postService.deletePostById(id);
    }

    public boolean updatePostTitle(final int id, final String title) {
        return postService.updatePostTitle(id, title);
    }

    public List<Post> searchPostsByKeyword(final String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }
}
