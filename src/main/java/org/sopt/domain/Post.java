package org.sopt.domain;

public class Post {
    private int id;
    private String title;

    public Post(int id, String title) {
        if(isTitleBlank(title)) {
            throw new IllegalArgumentException("⚠️ 게시글 제목은 비워둘 수 없습니다!");
        }
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    private boolean isTitleBlank(String title) {
        return title.isBlank();
    }
}