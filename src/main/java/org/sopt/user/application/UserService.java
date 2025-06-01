package org.sopt.user.application;

import org.sopt.user.application.dto.request.CreateUserServiceRequest;
import org.sopt.user.domain.User;
import org.sopt.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(CreateUserServiceRequest createUserServiceRequest) {
        User user = new User(createUserServiceRequest.name());
        userRepository.save(user);

        return user.getId();
    }
}
