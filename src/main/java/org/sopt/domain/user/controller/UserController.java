package org.sopt.domain.user.controller;

import java.net.URI;
import org.sopt.global.common.response.ApiResponse;
import org.sopt.domain.user.dto.request.CreateUserRequest;
import org.sopt.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> signUp(@RequestBody CreateUserRequest createUserRequest) {
        Long createdId = userService.createUser(createUserRequest);
        URI location = URI.create("/users/" + createdId);

        return ResponseEntity.created(location).body(ApiResponse.created());
    }
}
