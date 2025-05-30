package org.sopt.user.presentation;

import static org.sopt.user.presentation.mapper.UserRequestMapper.toCreateUserServiceRequest;
import static org.sopt.user.presentation.message.UserMessage.CREATED_SUCCESS;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.global.response.ApiResponse;
import org.sopt.user.application.dto.request.CreateUserServiceRequest;
import org.sopt.user.presentation.dto.request.CreateUserRequest;
import org.sopt.user.application.command.UserCommandService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserCommandService userCommandService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> signUp(@Valid @RequestBody CreateUserRequest createUserRequest) {
        CreateUserServiceRequest serviceRequest = toCreateUserServiceRequest(createUserRequest);
        Long createdId = userCommandService.createUser(serviceRequest);
        URI location = URI.create("/api/v1/users/" + createdId);

        return ResponseEntity.created(location).body(ApiResponse.created(CREATED_SUCCESS));
    }
}
