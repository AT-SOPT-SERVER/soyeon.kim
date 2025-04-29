package org.sopt.domain.post.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import org.sopt.global.error.BusinessException;
import org.sopt.global.util.GraphemeClusterUtil;
import org.sopt.domain.post.exception.PostErrorCode;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    public Post() {

    }

    public Post(String title, String content) {
        validateAll(title, content);
        this.title = title;
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
        if(content.isBlank()) {
            throw new BusinessException(PostErrorCode.INVALID_BLANK_CONTENT);
        }
    }

    private void isTitleBlank(String title) {
        if (title.isBlank()) {
            throw new BusinessException(PostErrorCode.INVALID_BLANK_TITLE);
        }
    }

    private void isTitleLessThan30(String title) {
        if (GraphemeClusterUtil.countGraphemeClusters(title) > 30) {
            throw new BusinessException(PostErrorCode.INVALID_TITLE_LENGTH);
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

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}
