package org.sopt.post.application.command;

import static org.sopt.post.domain.exception.PostErrorCode.POST_NOT_FOUND;
import static org.sopt.post.domain.exception.PostErrorCode.POST_UPDATE_UNAUTHORIZED;

import lombok.RequiredArgsConstructor;
import org.sopt.global.error.BusinessException;
import org.sopt.post.application.dto.request.CreatePostServiceRequest;
import org.sopt.post.application.dto.request.UpdatePostServiceRequest;
import org.sopt.post.application.validator.PostValidator;
import org.sopt.post.domain.Post;
import org.sopt.post.domain.exception.PostException;
import org.sopt.post.infrastructure.repository.PostRepository;
import org.sopt.user.domain.User;
import org.sopt.user.domain.exception.UserErrorCode;
import org.sopt.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostCommandService {

    private final PostRepository postRepository;
    private final PostValidator postValidator;
    private final UserRepository userRepository;

    @Transactional
    public Long createPost(Long userId, CreatePostServiceRequest request) {
        User user = getUser(userId);
        postValidator.validateAll(userId, request.title());

        Post post = Post.create(user, request.title(), request.content(), request.tag());
        postRepository.save(post);

        return post.getId();
    }

    @Transactional
    public void updatePostTitle(Long userId, Long postId, UpdatePostServiceRequest request) {
        Post post = getPost(postId);
        validateOwnership(userId, post);
        postValidator.validateAll(userId, request.title());

        post.updateTitle(request.title());
    }

    @Transactional
    public void deletePostById(Long userId, Long postId) {
        Post post = getPost(postId);
        validateOwnership(userId, post);

        post.softDelete();
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                   .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));
    }

    private Post getPost(Long id) {
        return postRepository.findByIdAndDeletedFalse(id)
                   .orElseThrow(() -> new PostException(POST_NOT_FOUND));
    }

    private void validateOwnership(Long userId, Post post) {
        if (!post.isOwnedBy(userId)) {
            throw new PostException(POST_UPDATE_UNAUTHORIZED);
        }
    }
}
