package org.sopt.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "사용자 이름은 비워둘 수 없습니다.")
    private String name;
}
