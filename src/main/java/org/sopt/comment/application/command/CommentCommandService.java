package org.sopt.comment.application.command;

import static org.sopt.comment.application.exception.CommentErrorCode.COMMENT_NOT_FOUND;
import static org.sopt.comment.application.exception.CommentErrorCode.COMMENT_UPDATE_UNAUTHORIZED;
import static org.sopt.post.application.exception.PostErrorCode.POST_NOT_FOUND;
import static org.sopt.user.application.exception.UserErrorCode.USER_NOT_FOUND;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.application.dto.request.CreateCommentServiceRequest;
import org.sopt.comment.application.dto.request.UpdateCommentServiceRequest;
import org.sopt.comment.domain.Comment;
import org.sopt.comment.infrastructure.repository.CommentRepository;
import org.sopt.global.error.BusinessException;
import org.sopt.post.domain.Post;
import org.sopt.post.infrastructure.repository.PostRepository;
import org.sopt.user.domain.User;
import org.sopt.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentCommandService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long createComment(CreateCommentServiceRequest createCommentServiceRequest) {
        User user = getUser(createCommentServiceRequest.getUserId());
        Post post = getPost(createCommentServiceRequest.getPostId());

        Comment comment = Comment.create(user, post, createCommentServiceRequest.getContent());
        commentRepository.save(comment);

        return comment.getId();
    }

    @Transactional
    public void updateComment(UpdateCommentServiceRequest updateCommentServiceRequest) {
        Long userId = updateCommentServiceRequest.getUserId();
        Long commentId = updateCommentServiceRequest.getCommentId();

        validateUserExists(userId);
        Comment comment = validateAuthorizedAndGetComment(userId, commentId);

        comment.updateContent(updateCommentServiceRequest.getContent());
    }

    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        validateUserExists(userId);
        Comment comment = validateAuthorizedAndGetComment(userId, commentId);

        if (comment.isDeleted()) {
            throw new BusinessException(COMMENT_NOT_FOUND);
        }

        comment.softDelete();
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                   .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                   .orElseThrow(() -> new BusinessException(POST_NOT_FOUND));
    }

    private void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new BusinessException(USER_NOT_FOUND);
        }
    }

    private Comment validateAuthorizedAndGetComment(Long userId, Long commentId) {
        Comment comment = validateCommentExists(commentId);
        if (!comment.hasOwnership(userId)) {
            throw new BusinessException(COMMENT_UPDATE_UNAUTHORIZED);
        }
        return comment;

    }

    private Comment validateCommentExists(Long commentId) {
        return commentRepository.findByIdAndDeletedFalse(commentId)
                   .orElseThrow(() -> new BusinessException(COMMENT_NOT_FOUND));
    }
}
