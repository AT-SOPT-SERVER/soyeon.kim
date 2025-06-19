package org.sopt.comment.application.query;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.infrastructure.repository.CommentLikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentLikeQueryService {

    private final CommentLikeRepository commentLikeRepository;
}
