package org.sopt.domain.post.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import org.sopt.domain.user.domain.User;
import org.sopt.global.error.BusinessException;
import org.sopt.global.util.GraphemeClusterUtil;
import org.sopt.domain.post.exception.PostErrorCode;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    public Post() {

    }

    public Post(User user, String title, String content) {
        validateAll(title, content);
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public void updateTitle(String title) {
        this.title = title;
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

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public User getUser() {
        return this.user;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}
