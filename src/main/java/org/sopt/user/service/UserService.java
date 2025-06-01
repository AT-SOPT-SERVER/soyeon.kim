package org.sopt.user.service;

import org.sopt.user.domain.User;
import org.sopt.user.presentation.dto.request.CreateUserRequest;
import org.sopt.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(CreateUserRequest createUserRequest) {
        User user = new User(createUserRequest.getName());
        userRepository.save(user);

        return user.getId();
    }
}
