package org.sopt.domain.post.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findFirstByOrderByCreatedAtDesc();

    List<Post> findAllByOrderByCreatedAtDesc();

    Optional<Post> findPostById(Long id);

    Optional<Post> findPostByTitle(String title);

    List<Post> findPostsByTitleContaining(String keyword);
}
