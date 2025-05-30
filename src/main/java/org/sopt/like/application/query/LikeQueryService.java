package org.sopt.like.application.query;

import lombok.RequiredArgsConstructor;
import org.sopt.like.infrastructure.repository.LikeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeQueryService {

    private final LikeRepository likeRepository;

}
