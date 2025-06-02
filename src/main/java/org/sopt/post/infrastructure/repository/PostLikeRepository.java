package org.sopt.post.infrastructure.repository;

import java.util.List;
import org.sopt.post.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existsPostLikeByUserIdAndPostId(Long userId, Long postId);

    int countByPostId(Long postId);

    List<PostLike> findAllByPostId(Long postId);
}
