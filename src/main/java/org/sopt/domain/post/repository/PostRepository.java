package org.sopt.domain.post.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.domain.post.domain.Post;
import org.sopt.domain.post.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findFirstByUser_IdOrderByCreatedAtDesc(Long userId);

    List<Post> findAllByOrderByCreatedAtDesc();

    Optional<Post> findPostById(Long id);

    Optional<Post> findPostByTitle(String title);

    List<Post> findPostsByTitleContaining(String keyword);

    List<Post> findPostsByUser_nameContaining(String keyword);

    List<Post> findPostsByTag(Tag tag);
}
