package org.sopt.post.application.command;

import lombok.RequiredArgsConstructor;
import org.sopt.post.infrastructure.repository.PostLikeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeCommandService {

    private final PostLikeRepository postLikeRepository;
}
