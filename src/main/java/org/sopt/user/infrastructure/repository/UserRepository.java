package org.sopt.user.infrastructure.repository;

import org.sopt.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
