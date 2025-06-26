package org.sopt.comment.presentation;

import static org.sopt.comment.presentation.message.CommentLikeMessage.CREATED_SUCCESS;
import static org.sopt.comment.presentation.message.CommentLikeMessage.RETRIEVED_SUCCESS;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.application.command.CommentLikeCommandService;
import org.sopt.comment.application.dto.response.GetCommentLikesCountServiceResponse;
import org.sopt.comment.application.query.CommentLikeQueryService;
import org.sopt.comment.presentation.dto.response.GetCommentLikesCountResponse;
import org.sopt.comment.presentation.mapper.CommentLikeResponseMapper;
import org.sopt.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@RestController
public class CommentLikeController {

    private final CommentLikeCommandService commentLikeCommandService;
    private final CommentLikeQueryService commentLikeQueryService;

    @PostMapping("/{comment-id}/likes")
    public ResponseEntity<ApiResponse<Void>> postCommentLikes(
        @PathVariable(name = "comment-id") Long commentId,
        @RequestHeader Long userId
    ) {
        commentLikeCommandService.likeComment(userId, commentId);

        return ResponseEntity.ok(ApiResponse.created(CREATED_SUCCESS));
    }

    @GetMapping("/{comment-id}/likes")
    public ResponseEntity<ApiResponse<GetCommentLikesCountResponse>> getCommentLikesCount(
        @PathVariable(name = "comment-id") Long commentId
    ) {
        GetCommentLikesCountServiceResponse serviceResponse = commentLikeQueryService.getCommentLikesCount(commentId);
        GetCommentLikesCountResponse response =
            CommentLikeResponseMapper.toGetCommentLikesCountResponse(serviceResponse);

        return ResponseEntity.ok(ApiResponse.ok(RETRIEVED_SUCCESS, response));
    }

    // TODO 댓글 좋아요 취소

}
