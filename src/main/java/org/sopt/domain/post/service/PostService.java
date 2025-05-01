package org.sopt.domain.post.service;

import java.util.stream.Collectors;
import org.sopt.domain.post.domain.Tag;
import org.sopt.domain.post.dto.request.CreatePostRequest;
import org.sopt.domain.post.dto.request.UpdatePostRequest;
import org.sopt.domain.post.dto.response.GetAllPostsResponse;
import org.sopt.domain.post.dto.response.GetDetailedPostResponse;
import org.sopt.domain.post.dto.response.GetSimplePostResponse;
import org.sopt.domain.post.dto.response.SearchPostResponse;
import org.sopt.domain.post.dto.response.SearchResultResponse;
import org.sopt.domain.user.domain.User;
import org.sopt.domain.user.exception.UserErrorCode;
import org.sopt.domain.user.repository.UserRepository;
import org.sopt.global.error.BusinessException;
import org.sopt.domain.post.domain.Post;
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
        Post post = new Post(user, title, createPostRequest.content(), Tag.fromKoreanName(createPostRequest.tag()));
        postRepository.save(post);

        user.getPosts().add(post);

        return post.getId();
    }

    public GetAllPostsResponse getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        return new GetAllPostsResponse(posts.stream()
                .map(GetSimplePostResponse::from)
                .collect(Collectors.toList()));
    }

    public GetDetailedPostResponse getPostById(Long id) {
        Post post = postRepository.findPostById(id)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));

        return GetDetailedPostResponse.from(post);
    }

    @Transactional
    public void deletePostById(Long userId, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));
        validateIsWriter(userId, post);

        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePostTitle(Long userId, Long id, UpdatePostRequest postRequest) {
        Post post = postRepository.findPostById(id)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));
        validateIsWriter(userId, post);

        String title = postRequest.title();
        postValidator.validateAll(title);
        post.updateTitle(title);
    }

    public SearchResultResponse searchPostsByKeyword(String keyword, String type) {
        return switch (type) {
            case "title" -> searchPostsByTitle(keyword);
            case "user" -> searchPostsByUser(keyword);
            case "tag" -> searchPostsByTag(keyword);
            default -> throw new BusinessException(PostErrorCode.INVALID_SEARCH_TYPE);
        };
    }

    private void validateIsWriter(Long userId, Post post) {
        if (!post.getUser().getId().equals(userId)) {
            throw new BusinessException(PostErrorCode.POST_UPDATE_UNAUTHORIZED);
        }
    }

    private SearchResultResponse searchPostsByTitle(String keyword) {
        List<Post> posts = postRepository.findPostsByTitleContaining(keyword);

        return new SearchResultResponse(posts.stream()
                .map(SearchPostResponse::from)
                .collect(Collectors.toList()));
    }

    private SearchResultResponse searchPostsByUser(String keyword) {
        List<Post> posts = postRepository.findPostsByUser_nameContaining(keyword);

        return new SearchResultResponse(posts.stream()
                .map(SearchPostResponse::from)
                .collect(Collectors.toList()));
    }

    private SearchResultResponse searchPostsByTag(String keyword) {
        List<Post> posts = postRepository.findPostsByTagContaining(keyword);

        return new SearchResultResponse(posts.stream()
                .map(SearchPostResponse::from)
                .collect(Collectors.toList()));
    }
}
