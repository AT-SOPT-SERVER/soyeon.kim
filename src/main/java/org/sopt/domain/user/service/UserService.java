package org.sopt.domain.user.service;

import org.sopt.domain.user.domain.User;
import org.sopt.domain.user.dto.CreateUserRequest;
import org.sopt.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(CreateUserRequest createUserRequest) {
        User user = new User(createUserRequest.name());
        userRepository.save(user);

        return user.getId();
    }
}
