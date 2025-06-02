package org.sopt.post.presentation;

import static org.sopt.post.presentation.message.PostLikeMessage.CREATED_SUCCESS;

import lombok.RequiredArgsConstructor;
import org.sopt.global.response.ApiResponse;
import org.sopt.post.application.command.PostLikeCommandService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/{post-id}/like")
    public ResponseEntity<ApiResponse<Void>> createPostLike(
        @RequestHeader Long userId,
        @PathVariable(name = "post-id") Long postId
    ) {
        postLikeCommandService.createPostLike(userId, postId);

        return ResponseEntity.ok(ApiResponse.ok(CREATED_SUCCESS));
    }
}
