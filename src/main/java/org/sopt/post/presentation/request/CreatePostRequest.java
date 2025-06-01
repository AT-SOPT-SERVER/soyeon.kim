package org.sopt.post.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostRequest {

    @NotBlank(message = "게시글 제목은 비워둘 수 없습니다.")
    private String title;

    @NotBlank(message = "게시글 본문은 비워둘 수 없습니다.")
    private String content;

    @NotBlank(message = "게시글 태그는 빈 값으로 둘 수 없습니다.")
    private String tag;

    @Builder
    public CreatePostRequest(String title, String content, String tag){
        this.title = title;
        this.content = content;
        this.tag = tag;
    }

}
