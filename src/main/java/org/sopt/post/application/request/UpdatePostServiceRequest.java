package org.sopt.post.application.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePostServiceRequest {

    private String title;

    @Builder
    public UpdatePostServiceRequest(String title) {
        this.title = title;
    }

}
