package org.sopt.comment.application.query;

import static org.sopt.comment.domain.exception.CommentErrorCode.COMMENT_NOT_FOUND;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.application.dto.response.GetCommentLikesCountServiceResponse;
import org.sopt.comment.domain.Comment;
import org.sopt.comment.domain.exception.CommentException;
import org.sopt.comment.infrastructure.repository.CommentLikeRepository;
import org.sopt.comment.infrastructure.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentLikeQueryService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    public GetCommentLikesCountServiceResponse getCommentLikesCount(Long commentId) {
        Comment comment = getCommentOrThrow(commentId);
        int count = commentLikeRepository.countByComment(comment);

        return new GetCommentLikesCountServiceResponse(count);
    }

    private Comment getCommentOrThrow(Long commentId) {
        return commentRepository.findById(commentId)
                   .orElseThrow(() -> new CommentException(COMMENT_NOT_FOUND));
    }
}
