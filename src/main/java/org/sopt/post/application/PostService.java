package org.sopt.post.application;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sopt.post.application.request.CreatePostServiceRequest;
import org.sopt.post.domain.Tag;
import org.sopt.post.presentation.request.UpdatePostRequest;
import org.sopt.post.presentation.response.GetAllPostsResponse;
import org.sopt.post.presentation.response.GetDetailedPostResponse;
import org.sopt.post.presentation.response.GetSimplePostResponse;
import org.sopt.post.presentation.response.SearchResultResponse;
import org.sopt.user.domain.User;
import org.sopt.user.presentation.exception.UserErrorCode;
import org.sopt.user.repository.UserRepository;
import org.sopt.global.error.BusinessException;
import org.sopt.post.domain.Post;
import org.sopt.post.presentation.exception.PostErrorCode;
import org.sopt.post.infrastructure.repository.PostRepository;

import java.util.List;
import org.sopt.post.application.validator.PostValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostValidator postValidator;
    private final UserRepository userRepository;

    @Transactional
    public Long createPost(Long userId, CreatePostServiceRequest serviceRequest) {
        validateMissingUser(userId);
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        String title = serviceRequest.getTitle();
        postValidator.validateAll(userId, title);

        Post post = new Post(user, title, serviceRequest.getContent(), Tag.fromKoreanName(serviceRequest.getTag()));
        postRepository.save(post);

        return post.getId();
    }

    public GetAllPostsResponse getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        return new GetAllPostsResponse(posts.stream()
                .map(GetSimplePostResponse::from)
                .collect(Collectors.toList()));
    }

    public GetDetailedPostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
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
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));
        validateCanUpdate(userId, post);

        String title = postRequest.getTitle();
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
