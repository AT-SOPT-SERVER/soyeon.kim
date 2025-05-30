package org.sopt.domain.user.controller;

import org.sopt.global.response.ApiResponse;
import org.sopt.domain.user.dto.request.CreateUserRequest;
import org.sopt.domain.user.service.UserService;

import org.springframework.http.HttpStatus;
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
    public ApiResponse<Void> signUp(@RequestBody CreateUserRequest createUserRequest) {
        Long createdId = userService.createUser(createUserRequest);

        return ApiResponse.created("성공적으로 " + createdId + "번 유저를 생성했습니다.");
    }
}
