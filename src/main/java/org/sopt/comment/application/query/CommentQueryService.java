package org.sopt.comment.application.query;

import static org.sopt.post.domain.exception.PostErrorCode.POST_NOT_FOUND;
import static org.sopt.user.domain.exception.UserErrorCode.USER_NOT_FOUND;

import lombok.RequiredArgsConstructor;
import org.sopt.comment.application.dto.response.GetAllCommentsServiceResponse;
import org.sopt.comment.application.dto.response.GetCommentServiceResponse;
import org.sopt.comment.infrastructure.repository.CommentRepository;
import org.sopt.global.error.BusinessException;
import org.sopt.post.infrastructure.repository.PostRepository;
import org.sopt.user.infrastructure.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public GetAllCommentsServiceResponse getAllComments(Long userId, Long postId, int page, int size) {
        validateUserAndPost(userId, postId);

        Page<GetCommentServiceResponse> commentPage = commentRepository.findByPostIdAndDeletedFalse(
            postId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        ).map(GetCommentServiceResponse::from);

        return GetAllCommentsServiceResponse.from(commentPage.getContent(), commentPage);
    }

    private void validateUserAndPost(Long userId, Long postId) {
        validateUserExist(userId);
        validatePostExist(postId);
    }

    private void validateUserExist(Long userId) {
        if (userId == null || !userRepository.existsById(userId)) {
            throw new BusinessException(USER_NOT_FOUND);
        }
    }

    private void validatePostExist(Long postId) {
        if (postId == null || !postRepository.existsById(postId)) {
            throw new BusinessException(POST_NOT_FOUND);
        }
    }
}
