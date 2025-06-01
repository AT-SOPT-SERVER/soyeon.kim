package org.sopt.comment.application.command;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.infrastructure.repository.CommentRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentCommandService {

    private final CommentRepository commentRepository;
}
