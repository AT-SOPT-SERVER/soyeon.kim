package org.sopt.post.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.post.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existsPostLikeByUser_IdAndPost_Id(Long userId, Long postId);

    int countByPost_Id(Long postId);

    List<PostLike> findAllByPost_Id(Long postId);

    Optional<PostLike> findByUser_IdAndPost_Id(Long userId, Long postId);
}
