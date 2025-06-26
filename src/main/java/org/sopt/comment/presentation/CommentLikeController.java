package org.sopt.comment.presentation;

import static org.sopt.comment.presentation.message.CommentLikeMessage.CREATED_SUCCESS;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.application.command.CommentLikeCommandService;
import org.sopt.comment.application.query.CommentLikeQueryService;
import org.sopt.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@RestController
public class CommentLikeController {

    private final CommentLikeCommandService commentLikeCommandService;
    private final CommentLikeQueryService commentLikeQueryService;

    @RequestMapping("/{comment-id}/likes")
    public ResponseEntity<ApiResponse<Void>> postCommentLike(
        @PathVariable(name = "comment-id") Long commentId,
        @RequestHeader Long userId
    ) {
        commentLikeCommandService.likeComment(userId, commentId);

        return ResponseEntity.ok(ApiResponse.created(CREATED_SUCCESS));
    }

    // TODO 댓글 좋아요 수 반환

    // TODO 댓글 좋아요 취소

}
