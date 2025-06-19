package org.sopt.comment.presentation;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.application.command.CommentLikeCommandService;
import org.sopt.comment.application.query.CommentLikeQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@RestController
public class CommentLikeController {

    private final CommentLikeCommandService commentLikeCommandService;
    private final CommentLikeQueryService commentLikeQueryService;

}
