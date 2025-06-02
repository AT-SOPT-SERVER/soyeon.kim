package org.sopt.comment.domain;

import static org.sopt.comment.application.exception.CommentErrorCode.INVALID_CONTENT_BLANK;
import static org.sopt.comment.application.exception.CommentErrorCode.INVALID_CONTENT_LENGTH;
import static org.sopt.global.util.GraphemeClusterUtil.countGraphemeClusters;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.global.entity.BaseEntity;
import org.sopt.global.error.BusinessException;
import org.sopt.post.domain.Post;
import org.sopt.user.domain.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    @Column(nullable = false)
    private boolean deleted = false;

    private LocalDateTime deletedAt;

    private Comment(User user, Post post, String content) {
        validate(content);
        this.user = user;
        this.post = post;
        this.content = content;
    }

    public static Comment create(User user, Post post, String content) {
        return new Comment(user, post, content);
    }

    public boolean hasOwnership(Long userId) {
        return this.user.getId().equals(userId);
    }

    public void updateContent(String content) {
        validate(content);
        this.content = content;
    }

    public void softDelete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }

    private void validate(String content) {
        validateContentBlank(content);
        validateContentLength(content);
    }

    private void validateContentBlank(String content) {
        if (content == null || content.isBlank()) {
            throw new BusinessException(INVALID_CONTENT_BLANK);
        }
    }

    private void validateContentLength(String content) {
        if (countGraphemeClusters(content) > 300) {
            throw new BusinessException(INVALID_CONTENT_LENGTH);
        }
    }
}
