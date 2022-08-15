package com.ballnet.postservice.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles = "test")
@SpringBootTest
@EnableAutoConfiguration
class CommentServiceTest {

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void findAllByPostId() {
  }

  @Test
  void findAllByUserId() {
  }

  @Test
  void findById() {
  }

  @Test
  void add() {
  }
}