package org.sopt.domain.post.service;

import java.util.Optional;
import java.util.stream.Collectors;
import org.sopt.global.error.BusinessException;
import org.sopt.domain.post.domain.Post;
import org.sopt.domain.post.dto.PostRequest;
import org.sopt.domain.post.dto.PostResponse;
import org.sopt.domain.post.exception.PostErrorCode;
import org.sopt.domain.post.repository.PostRepository;

import java.util.List;
import org.sopt.domain.post.validator.PostValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        Optional<Post> post = postRepository.findPostById(id);
        if (post.isEmpty()) {
            throw new BusinessException(PostErrorCode.POST_NOT_FOUND);
        }

        return PostResponse.from(post.get());
    }

    @Transactional
    public void deletePostById(Long id) {
        if (postRepository.findById(id).isEmpty()) {
            throw new BusinessException(PostErrorCode.POST_NOT_FOUND);
        }
        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePostTitle(Long id, PostRequest postRequest) {
        Optional<Post> post = postRepository.findPostById(id);
        if (post.isEmpty()) {
            throw new BusinessException(PostErrorCode.POST_NOT_FOUND);
        }

        String title = postRequest.title();
        postValidator.validateAll(title);
        post.get().updateTitle(title);
    }

    public List<PostResponse> searchPostsByKeyword(String keyword) {
        List<Post> posts = postRepository.findPostsByTitleContaining(keyword);

        return posts.stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }
}
