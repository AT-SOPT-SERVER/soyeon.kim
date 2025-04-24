package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.repository.PostRepository;

import java.util.List;
import org.sopt.validator.PostValidator;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostValidator postValidator;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
        this.postValidator = new PostValidator(postRepository);
    }

    public Long createPost(PostRequest postRequest) {
        String title = postRequest.title();

        postValidator.validateAll(title);
        Post post = new Post(title);
        postRepository.save(post);

        return post.getId();
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findPostById(id).get();
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    public void updatePostTitle(Long id, String title) {
        postValidator.validateAll(title);
        // postRepository.updateTitleById(id, title);
    }

    public void searchPostsByKeyword(String keyword) {
        // return postRepository.findPostsByKeyword(keyword);
    }
}
