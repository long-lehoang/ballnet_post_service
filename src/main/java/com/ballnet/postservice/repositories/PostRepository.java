package com.ballnet.postservice.repositories;

import com.ballnet.postservice.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
public interface PostRepository extends JpaRepository<PostEntity, Long> {
  List<PostEntity> findByUserId(Long userId);
}
