package org.sopt.post.application.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostServiceRequest {

    private String title;

    private String content;

    private String tag;

    @Builder
    public CreatePostServiceRequest(String title, String content, String tag){
        this.title = title;
        this.content = content;
        this.tag = tag;
    }
}
