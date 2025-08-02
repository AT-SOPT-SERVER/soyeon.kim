package org.sopt.comment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.global.entity.BaseEntity;
import org.sopt.user.domain.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
    name = "comment_like",
    uniqueConstraints = {
        @UniqueConstraint(name = "idx_unique_user_comment_like", columnNames = {"user_id", "comment_id"})
    }
)
public class CommentLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    private CommentLike(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public static CommentLike create(User user, Comment comment) {
        return CommentLike.builder()
                   .user(user)
                   .comment(comment)
                   .build();
    }
}
