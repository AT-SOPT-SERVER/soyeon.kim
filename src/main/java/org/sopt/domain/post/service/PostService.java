package org.sopt.domain.post.service;

import java.util.Optional;
import java.util.stream.Collectors;
import org.sopt.domain.post.dto.request.CreatePostRequest;
import org.sopt.domain.post.dto.request.UpdatePostRequest;
import org.sopt.domain.post.dto.response.GetPostsResponse;
import org.sopt.domain.user.domain.User;
import org.sopt.domain.user.exception.UserErrorCode;
import org.sopt.domain.user.repository.UserRepository;
import org.sopt.global.error.BusinessException;
import org.sopt.domain.post.domain.Post;
import org.sopt.domain.post.dto.response.PostResponse;
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
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postValidator = new PostValidator(postRepository);
        this.userRepository = userRepository;
    }

    @Transactional
    public Long createPost(Long userId, CreatePostRequest createPostRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        String title = createPostRequest.title();
        postValidator.validateAll(title);
        Post post = new Post(user, title, createPostRequest.content());
        postRepository.save(post);

        user.getPosts().add(post);

        return post.getId();
    }

    public List<GetPostsResponse> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        return posts.stream()
                .map(GetPostsResponse::from)
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
    public void updatePostTitle(Long id, UpdatePostRequest postRequest) {
        Optional<Post> post = postRepository.findPostById(id);
        if (post.isEmpty()) {
            throw new BusinessException(PostErrorCode.POST_NOT_FOUND);
        }

        String title = postRequest.title();
        postValidator.validateAll(title);
        post.get().updateTitle(title);
    }

    // TODO 서비스 코드 getAll 과 합칠지 고민해 보기
    // TODO 작성자로도 검색할 수 있게! or 조건으로 추가하기
    //
    public List<GetPostsResponse> searchPostsByKeyword(String keyword) {
        List<Post> posts = postRepository.findPostsByTitleContaining(keyword);

        return posts.stream()
                .map(GetPostsResponse::from)
                .collect(Collectors.toList());
    }
}
