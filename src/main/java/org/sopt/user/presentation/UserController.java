package org.sopt.user.presentation;

import static org.sopt.user.presentation.message.UserMessage.CREATED_SUCCESS;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.global.response.ApiResponse;
import org.sopt.user.presentation.dto.request.CreateUserRequest;
import org.sopt.user.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> signUp(@Valid @RequestBody CreateUserRequest createUserRequest) {
        Long createdId = userService.createUser(createUserRequest);
        URI location = URI.create("/api/v1/users/" + createdId);

        return ResponseEntity.created(location).body(ApiResponse.created(CREATED_SUCCESS));
    }
}
