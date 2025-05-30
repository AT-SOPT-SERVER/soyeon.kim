package org.sopt.comment.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostIdAndDeletedFalse(Long postId);

    Optional<Comment> findByIdAndDeletedFalse(Long commentId);
}
