package org.sopt.comment.presentation;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.application.command.CommentCommandService;
import org.sopt.comment.application.query.CommentQueryService;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;
}
