package org.sopt.post.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.post.domain.Post;
import org.sopt.post.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findFirstByUser_IdAndDeletedFalseOrderByCreatedAtDesc(Long userId);

    List<Post> findAllByDeletedFalseOrderByCreatedAtDesc();

    Optional<Post> findPostByTitleAndDeletedFalse(String title);

    List<Post> findPostsDeletedFalseAndByTitleContaining(String keyword);

    List<Post> findPostsByDeletedFalseAndUser_nameContaining(String keyword);

    List<Post> findPostsDeletedFalseAndByTag(Tag tag);

    Optional<Post> findByIdAndDeletedFalse(Long postId);
}
