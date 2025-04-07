package org.sopt.domain;

public class Post {
    private final int id;
    private String title;

    public Post(final int id, final String title) {
        validateTitle(title);
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void updateTitle(final String newTitle) {
        this.title = newTitle;
    }

    private void validateTitle(final String title) {
        isTitleBlank(title);
        isTitleLessThan30(title);
    }

    private void isTitleBlank(final String title) {
        if (title.isBlank()) {
            throw new IllegalArgumentException("⚠️ 게시글 제목은 비워둘 수 없습니다!");
        }
    }

    private void isTitleLessThan30(final String title) {
        if (title.length() > 30) {
            throw new IllegalArgumentException("⚠️ 게시글 제목은 30자가 넘을 수 없습니다!");
        }
    }
}