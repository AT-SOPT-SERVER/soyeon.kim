package org.sopt.user.presentation.mapper;

import org.sopt.user.application.dto.request.CreateUserServiceRequest;
import org.sopt.user.presentation.dto.request.CreateUserRequest;

public class UserRequestMapper {

    public static CreateUserServiceRequest toCreateUserServiceRequest(CreateUserRequest createUserRequest) {
        return new CreateUserServiceRequest(createUserRequest.getName());
    }
}
