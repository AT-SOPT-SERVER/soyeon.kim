package org.sopt.post.application.query;

import lombok.RequiredArgsConstructor;
import org.sopt.post.infrastructure.repository.PostLikeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeQueryService {

    private final PostLikeRepository postLikeRepository;
}
