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

    private LocalDateTime createdAt;

    public Post() {

    }

    public Post(String title) {
        validateTitle(title);
        this.title = title;
        this.createdAt = LocalDateTime.now();
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    private void validateTitle(String title) {
        isTitleBlank(title);
        isTitleLessThan30(title);
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

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}
