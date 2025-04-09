package org.sopt.domain;

import java.time.LocalDateTime;
import org.sopt.util.GraphemeClusterUtil;

public class Post {
    private final int id;
    private String title;
    private final LocalDateTime createdAt;

    public Post(final int id, final String title) {
        validateTitle(title);
        this.id = id;
        this.title = title;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    private void validateTitle(String title) {
        isTitleBlank(title);
        isTitleLessThan30(title);
    }

    private void isTitleBlank(String title) {
        if (title.isBlank()) {
            throw new IllegalArgumentException("⚠️ 게시글 제목은 비워둘 수 없습니다!");
        }
    }

    private void isTitleLessThan30(String title) {
        if (GraphemeClusterUtil.countGraphemeClusters(title) > 30) {
            throw new IllegalArgumentException("⚠️ 게시글 제목은 30자를 넘을 수 없습니다!");
        }
    }
}
