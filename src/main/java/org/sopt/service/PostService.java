package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;
import org.sopt.util.IdGenerator;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    private final IdGenerator idGenerator = new IdGenerator();

    public void createPost(final String title) {
        Post post = new Post(idGenerator.autoIncrement(), title);
        postRepository.save(post);
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

    public List<Post> searchPostsByKeyword(final String keyword) {
        return postRepository.findPostsByKeyword(keyword);
    }
}