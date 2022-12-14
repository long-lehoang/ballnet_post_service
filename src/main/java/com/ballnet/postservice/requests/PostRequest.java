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
public class PostRequest {
  @NotEmpty
  private String content;
  @NotEmpty
  private Long userId;
}
