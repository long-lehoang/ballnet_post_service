package com.ballnet.postservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
  private Long id;
  private String content;
  private Long userId;
  private Timestamp createdDatetime;
  private Timestamp updatedDatetime;
}
