package com.ballnet.postservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;
  private Long postId;
  private Long userId;
  @Builder.Default
  private Timestamp createdDatetime = new Timestamp(System.currentTimeMillis());
  private Timestamp updatedDatetime;
}
