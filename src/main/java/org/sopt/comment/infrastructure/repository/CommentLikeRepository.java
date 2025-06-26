package org.sopt.comment.infrastructure.repository;

import java.util.Optional;
import org.sopt.comment.domain.Comment;
import org.sopt.comment.domain.CommentLike;
import org.sopt.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    boolean existsByUserAndComment(User user, Comment comment);

    int countByComment(Comment comment);

    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}

