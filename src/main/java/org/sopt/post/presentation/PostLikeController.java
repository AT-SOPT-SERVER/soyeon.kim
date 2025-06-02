package org.sopt.post.presentation;

import lombok.RequiredArgsConstructor;
import org.sopt.post.infrastructure.repository.PostLikeRepository;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostLikeController {

    private final PostLikeRepository postLikeRepository;
}
