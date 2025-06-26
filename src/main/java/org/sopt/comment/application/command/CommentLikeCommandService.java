package org.sopt.comment.application.command;

import static org.sopt.comment.application.exception.CommentErrorCode.COMMENT_NOT_FOUND;
import static org.sopt.comment.application.exception.CommentLikeErrorCode.COMMENT_ALREADY_LIKED;
import static org.sopt.user.application.exception.UserErrorCode.USER_NOT_FOUND;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.domain.Comment;
import org.sopt.comment.domain.CommentLike;
import org.sopt.comment.infrastructure.repository.CommentLikeRepository;
import org.sopt.comment.infrastructure.repository.CommentRepository;
import org.sopt.global.error.BusinessException;
import org.sopt.user.domain.User;
import org.sopt.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentLikeCommandService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void likeComment(Long userId, Long commentId) {
        User user = getUserOrThrow(userId);
        Comment comment = getCommentOrThrow(commentId);
        canLikeComment(user, comment);

        CommentLike commentLike = CommentLike.create(user, comment);

        commentLikeRepository.save(commentLike);
    }

    private void canLikeComment(User user, Comment comment) {
        if (commentLikeRepository.existsByUserAndComment(user, comment)) {
            throw new BusinessException(COMMENT_ALREADY_LIKED);
        }
    }

    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                   .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
    }

    private Comment getCommentOrThrow(Long commentId) {
        return commentRepository.findById(commentId)
                   .orElseThrow(() -> new BusinessException(COMMENT_NOT_FOUND));
    }
}
