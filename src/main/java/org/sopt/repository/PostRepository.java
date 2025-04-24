package org.sopt.repository;

import java.util.Optional;
import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findFirstByOrderByCreatedAtDesc();

    Optional<Post> findPostById(Long id);

    Optional<Post> findPostByTitle(String title);

    void deleteById(@NonNull Long id);
}
