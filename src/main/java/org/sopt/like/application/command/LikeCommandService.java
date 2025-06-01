package org.sopt.like.application.command;

import lombok.RequiredArgsConstructor;
import org.sopt.like.infrastructure.repository.LikeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeCommandService {

    private final LikeRepository likeRepository;
}
