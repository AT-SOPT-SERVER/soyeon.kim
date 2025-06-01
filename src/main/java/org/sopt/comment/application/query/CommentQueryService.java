package org.sopt.comment.application.query;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.infrastructure.repository.CommentRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentQueryService {

    private final CommentRepository commentRepository;
}
