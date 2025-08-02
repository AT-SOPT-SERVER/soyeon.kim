package org.sopt.post.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostRequest {

    @NotBlank(message = "게시글 제목은 비워둘 수 없습니다.")
    private String title;
}
