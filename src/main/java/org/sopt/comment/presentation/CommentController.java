package org.sopt.comment.presentation;

import static org.sopt.comment.presentation.mapper.CommentRequestMapper.toCreateCommentServiceRequest;
import static org.sopt.comment.presentation.mapper.CommentResponseMapper.toGetAllCommentsResponse;
import static org.sopt.comment.presentation.message.CommentMessage.CREATED_SUCCESS;
import static org.sopt.comment.presentation.message.CommentMessage.RETRIEVED_ALL_SUCCESS;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.comment.application.command.CommentCommandService;
import org.sopt.comment.application.dto.request.CreateCommentServiceRequest;
import org.sopt.comment.application.dto.response.GetAllCommentsServiceResponse;
import org.sopt.comment.application.query.CommentQueryService;
import org.sopt.comment.presentation.dto.request.CreateCommentRequest;
import org.sopt.comment.presentation.dto.response.GetAllCommentsResponse;
import org.sopt.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @PostMapping("/{post-id}/comments")
    public ResponseEntity<ApiResponse<Void>> createComment(
        @RequestHeader Long userId,
        @PathVariable(name = "post-id") Long postId,
        @Valid @RequestBody CreateCommentRequest createCommentRequest
    ) {
        CreateCommentServiceRequest serviceRequest = toCreateCommentServiceRequest(
            userId,
            postId,
            createCommentRequest);
        Long commentId = commentCommandService.createComment(serviceRequest);
        URI location = URI.create("/api/v1/posts/" + postId + "/comments" + commentId);

        return ResponseEntity.created(location).body(ApiResponse.created(CREATED_SUCCESS));
    }

    @GetMapping("/{post-id}/comments")
    public ResponseEntity<ApiResponse<GetAllCommentsResponse>> getAllComments(
        @RequestHeader Long userId,
        @PathVariable(name = "post-id") Long postId
    ) {
        GetAllCommentsServiceResponse serviceResponse = commentQueryService.getAllComments(userId, postId);
        GetAllCommentsResponse response = toGetAllCommentsResponse(serviceResponse);

        return ResponseEntity.ok(ApiResponse.ok(RETRIEVED_ALL_SUCCESS, response));
    }
}
