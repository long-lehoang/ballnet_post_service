package com.ballnet.postservice.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
  @NotEmpty
  private String content;
  @NotEmpty
  private Long postId;
  @NotEmpty
  private Long userId;
}
