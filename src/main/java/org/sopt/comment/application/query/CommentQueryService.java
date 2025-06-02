package org.sopt.comment.application.query;

import static org.sopt.post.application.exception.PostErrorCode.POST_NOT_FOUND;
import static org.sopt.user.application.exception.UserErrorCode.USER_NOT_FOUND;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.comment.application.dto.response.GetAllCommentsServiceResponse;
import org.sopt.comment.application.dto.response.GetCommentServiceResponse;
import org.sopt.comment.domain.Comment;
import org.sopt.comment.infrastructure.repository.CommentRepository;
import org.sopt.global.error.BusinessException;
import org.sopt.post.infrastructure.repository.PostRepository;
import org.sopt.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public GetAllCommentsServiceResponse getAllComments(Long userId, Long postId) {
        validateUserAndPost(userId, postId);

        List<Comment> comments = commentRepository.findByPostIdAndDeletedFalse(postId);
        List<GetCommentServiceResponse> results = comments.stream()
                                                      .map(GetCommentServiceResponse::from)
                                                      .toList();

        return new GetAllCommentsServiceResponse(results);
    }

    private void validateUserAndPost(Long userId, Long postId) {
        validateUserExist(userId);
        validatePostExist(postId);
    }

    private void validateUserExist(Long userId) {
        if (userId == null ||!userRepository.existsById(userId)) {
            throw new BusinessException(USER_NOT_FOUND);
        }
    }

    private void validatePostExist(Long postId) {
        if (postId == null || !postRepository.existsById(postId)) {
            throw new BusinessException(POST_NOT_FOUND);
        }
    }
}
