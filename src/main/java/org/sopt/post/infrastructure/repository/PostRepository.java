package org.sopt.post.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.post.domain.Post;
import org.sopt.post.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findFirstByUser_IdAndDeletedFalseOrderByCreatedAtDesc(Long userId);

    Optional<Post> findPostByTitleAndDeletedFalse(String title);

    List<Post> findPostsDeletedFalseAndByTitleContaining(String keyword);

    List<Post> findPostsByDeletedFalseAndUser_nameContaining(String keyword);

    List<Post> findPostsDeletedFalseAndByTag(Tag tag);

    Optional<Post> findByIdAndDeletedFalse(Long postId);

    boolean existsByIdAndDeletedFalse(Long postId);

    Page<Post> findAllByDeletedFalse(Pageable pageable);
}
