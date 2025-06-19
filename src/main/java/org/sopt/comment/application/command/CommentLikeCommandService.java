package org.sopt.comment.application.command;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.infrastructure.repository.CommentLikeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentLikeCommandService {

    private final CommentLikeRepository commentLikeRepository;
}
