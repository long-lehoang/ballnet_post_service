package com.ballnet.postservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
  private Long id;
  private String content;
  private Long postId;
  private Long userId;
  private Timestamp createdDatetime;
  private Timestamp updatedDatetime;
}
