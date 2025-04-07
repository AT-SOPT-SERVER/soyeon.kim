package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    private int postId = 1;

    public String createPost(final String title) {
        try{
            Post post = new Post(postId++, title);
            postRepository.save(post);
            return "✅ 게시글이 성공적으로 저장되었습니다.";
        } catch (IllegalArgumentException e) {
            postId--;
            return e.getMessage();
        }
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(final int id) {
        return postRepository.findPostById(id);
    }

    public boolean deletePostById(final int id) {
        return postRepository.delete(id);
    }

    public boolean updatePostTitle(final int id, final String title) {
        return postRepository.updateTitleById(id, title);
    }
}