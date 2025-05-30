package org.sopt.like.presentation;

import lombok.RequiredArgsConstructor;
import org.sopt.like.application.command.LikeCommandService;
import org.sopt.like.application.query.LikeQueryService;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeCommandService likeCommandService;
    private final LikeQueryService likeQueryService;
}
