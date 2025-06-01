package org.sopt.post.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.user.domain.User;
import org.sopt.global.entity.BaseEntity;
import org.sopt.global.error.BusinessException;
import org.sopt.global.util.GraphemeClusterUtil;
import org.sopt.post.presentation.exception.PostErrorCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseEntity {

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

    public boolean isOwnedBy(Long userId) {
        return this.user.getId().equals(userId);
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
        if (title.isBlank()) {
            throw new BusinessException(PostErrorCode.INVALID_TITLE_BLANK);
        }
    }

    private void isTitleLessThan30(String title) {
        if (GraphemeClusterUtil.countGraphemeClusters(title) > 30) {
            throw new BusinessException(PostErrorCode.INVALID_TITLE_LENGTH);
        }
    }

    private void isContentBlank(String content) {
        if (content.isBlank()) {
            throw new BusinessException(PostErrorCode.INVALID_CONTENT_BLANK);
        }
    }

    private void isContentLessThan1000(String content) {
        if (GraphemeClusterUtil.countGraphemeClusters(content) > 1000) {
            throw new BusinessException(PostErrorCode.INVALID_CONTENT_LENGTH);
        }
    }
}
