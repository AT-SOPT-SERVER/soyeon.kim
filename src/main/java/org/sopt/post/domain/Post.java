package org.sopt.post.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.comment.domain.Comment;
import org.sopt.user.domain.User;
import org.sopt.global.entity.BaseEntity;
import org.sopt.global.error.BusinessException;
import org.sopt.global.util.GraphemeClusterUtil;
import org.sopt.post.domain.exception.PostErrorCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseEntity {

    @OneToMany(mappedBy = "post") // soft-delete 사용하지 않는 경우 cascade, orphanRemove 추가
    private final List<Comment> commentList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Tag tag;

    @Column(nullable = false)
    private boolean deleted = false;

    private LocalDateTime deletedAt;

    public Post(User user, String title, String content, Tag tag) {
        validateAll(title, content);
        this.user = user;
        this.title = title;
        this.content = content;
        this.tag = tag;
    }

    public static Post create(User user, String title, String content, String tagName) {
        return new Post(user, title, content, Tag.fromKoreanName(tagName));
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public List<Comment> getActiveComments() {
        return commentList.stream()
                   .filter(comment -> !comment.isDeleted())
                   .toList();
    }

    public boolean isOwnedBy(Long userId) {
        return this.user.getId().equals(userId);
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }

    public void softDelete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
        for (Comment comment : commentList) {
            comment.softDelete();
        }
    }

    private void validateAll(String title, String content) {
        validateTitle(title);
        validateContent(content);
    }

    private void validateTitle(String title) {
        isTitleBlank(title);
        isTitleLessThan30(title);
    }

    private void validateContent(String content) {
        isContentBlank(content);
        isContentLessThan1000(content);
    }

    private void isTitleBlank(String title) {
        if (title == null || title.isBlank()) {
            throw new BusinessException(PostErrorCode.INVALID_TITLE_BLANK);
        }
    }

    private void isTitleLessThan30(String title) {
        if (GraphemeClusterUtil.countGraphemeClusters(title) > 30) {
            throw new BusinessException(PostErrorCode.INVALID_TITLE_LENGTH);
        }
    }

    private void isContentBlank(String content) {
        if (content == null || content.isBlank()) {
            throw new BusinessException(PostErrorCode.INVALID_CONTENT_BLANK);
        }
    }

    private void isContentLessThan1000(String content) {
        if (GraphemeClusterUtil.countGraphemeClusters(content) > 1000) {
            throw new BusinessException(PostErrorCode.INVALID_CONTENT_LENGTH);
        }
    }
}
