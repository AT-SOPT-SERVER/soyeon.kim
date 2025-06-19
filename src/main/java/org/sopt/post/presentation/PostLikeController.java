package org.sopt.post.presentation;

import static org.sopt.post.presentation.message.PostLikeMessage.COUNT_RETRIEVED_SUCCESS;
import static org.sopt.post.presentation.message.PostLikeMessage.CREATED_SUCCESS;
import static org.sopt.post.presentation.message.PostLikeMessage.DELETED_SUCCESS;
import static org.sopt.post.presentation.message.PostLikeMessage.USERS_RETRIEVED_SUCCESS;

import lombok.RequiredArgsConstructor;
import org.sopt.global.response.ApiResponse;
import org.sopt.post.application.command.PostLikeCommandService;
import org.sopt.post.application.dto.response.GetUsersLikedPostServiceResponse;
import org.sopt.post.application.dto.response.LikeCountServiceResponse;
import org.sopt.post.application.query.PostLikeQueryService;
import org.sopt.post.presentation.dto.response.GetUsersLikedResponse;
import org.sopt.post.presentation.dto.response.LikeCountResponse;
import org.sopt.post.presentation.mapper.PostLikeResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@RestController
public class PostLikeController {

    private final PostLikeCommandService postLikeCommandService;
    private final PostLikeQueryService postLikeQueryService;

    @PostMapping("/{post-id}/likes")
    public ResponseEntity<ApiResponse<Void>> createPostLike(
        @RequestHeader Long userId,
        @PathVariable(name = "post-id") Long postId
    ) {
        postLikeCommandService.createPostLike(userId, postId);

        return ResponseEntity.ok(ApiResponse.ok(CREATED_SUCCESS));
    }

    @GetMapping("/{post-id}/likes")
    public ResponseEntity<ApiResponse<GetUsersLikedResponse>> getUsersLikedByPostId(
        @PathVariable(name = "post-id") Long postId
    ) {
        GetUsersLikedPostServiceResponse serviceResponse = postLikeQueryService.getUsersLikedPost(postId);
        GetUsersLikedResponse response = PostLikeResponseMapper.toGetUsersLikedResponse(serviceResponse);

        return ResponseEntity.ok(ApiResponse.ok(USERS_RETRIEVED_SUCCESS, response));
    }

    @GetMapping("/{post-id}/likes/count")
    public ResponseEntity<ApiResponse<LikeCountResponse>> getLikeCountByPostId(
        @PathVariable(name = "post-id") Long postId
    ) {
        LikeCountServiceResponse serviceResponse = postLikeQueryService.getLikeCount(postId);
        LikeCountResponse response = PostLikeResponseMapper.toLikeCountResponse(serviceResponse);

        return ResponseEntity.ok(ApiResponse.ok(COUNT_RETRIEVED_SUCCESS, response));
    }

    // TODO 좋아요 반환 로직 -> 유저, 좋아요 수로 분리

    @DeleteMapping("/{post-id}/likes")
    public ResponseEntity<ApiResponse<Void>> dislikePost(
        @RequestHeader Long userId,
        @PathVariable(name = "post-id") Long postId
    ) {
        postLikeCommandService.dislikePost(userId, postId);

        return ResponseEntity.ok(ApiResponse.ok(DELETED_SUCCESS));
    }
}
