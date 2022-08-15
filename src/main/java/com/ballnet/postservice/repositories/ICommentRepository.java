package com.ballnet.postservice.repositories;

import com.ballnet.postservice.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
public interface ICommentRepository extends JpaRepository<CommentEntity, Long> {
  List<CommentEntity> findByPostId(Long postId);

  List<CommentEntity> findByUserId(Long userId);
}
