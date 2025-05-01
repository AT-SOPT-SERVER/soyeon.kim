package org.sopt.domain.post.service;

import java.util.stream.Collectors;
import org.sopt.domain.post.domain.Tag;
import org.sopt.domain.post.dto.request.CreatePostRequest;
import org.sopt.domain.post.dto.request.UpdatePostRequest;
import org.sopt.domain.post.dto.response.GetAllPostsResponse;
import org.sopt.domain.post.dto.response.GetDetailedPostResponse;
import org.sopt.domain.post.dto.response.GetSimplePostResponse;
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
        validateMissingUser(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        String title = createPostRequest.title();
        postValidator.validateAll(userId, title);
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
        validateMissingUser(userId);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));
        validateCanDelete(userId, post);

        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePostTitle(Long userId, Long id, UpdatePostRequest postRequest) {
        validateMissingUser(userId);
        Post post = postRepository.findPostById(id)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));
        validateCanUpdate(userId, post);

        String title = postRequest.title();
        postValidator.validateAll(userId, title);
        post.updateTitle(title);
    }

    public SearchResultResponse searchPostsByKeyword(String keyword, String type) {
        List<Post> posts = switch (type) {
            case "title" -> postRepository.findPostsByTitleContaining(keyword);
            case "user" -> postRepository.findPostsByUser_nameContaining(keyword);
            case "tag" -> postRepository.findPostsByTag(Tag.fromKoreanName(keyword));
            default -> throw new BusinessException(PostErrorCode.INVALID_SEARCH_TYPE);
        };

        return SearchResultResponse.from(posts);
    }

    private void validateMissingUser(Long userId) {
        if (userId == null) {
            throw new BusinessException(PostErrorCode.UNAUTHORIZED_USER);
        }
    }

    private void validateCanDelete(Long userId, Post post) {
        if (!post.getUser().getId().equals(userId)) {
            throw new BusinessException(PostErrorCode.POST_DELETE_UNAUTHORIZED);
        }
    }

    private void validateCanUpdate(Long userId, Post post) {
        if (!post.getUser().getId().equals(userId)) {
            throw new BusinessException(PostErrorCode.POST_UPDATE_UNAUTHORIZED);
        }
    }
}
