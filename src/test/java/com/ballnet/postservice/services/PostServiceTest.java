package com.ballnet.postservice.services;

import com.ballnet.postservice.entities.PostEntity;
import com.ballnet.postservice.repositories.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles(profiles = "test")
@SpringBootTest
@EnableAutoConfiguration
class PostServiceTest {

  @Autowired
  PostService postService;
  @Autowired
  PostRepository postRepository;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
    postRepository.deleteAll();
  }

  @Test
  void givenValidUserId_whenFindAll_thenSucceed() {
    var entity = PostEntity.builder()
        .id(1L)
        .content("Test")
        .userId(1L)
        .build();

    postRepository.save(entity);

    var entites = postService.findAllByUserId(entity.getUserId());
    assertFalse(CollectionUtils.isEmpty(entites));
  }

  @Test
  void givenInValidUserId_whenFindAll_thenFailed() {
    var entity = PostEntity.builder()
        .id(1L)
        .content("Test")
        .userId(1L)
        .build();

    postRepository.save(entity);

    var entites = postService.findAllByUserId(2L);
    assertTrue(CollectionUtils.isEmpty(entites));
  }

  @Test
  void add() {
  }
}