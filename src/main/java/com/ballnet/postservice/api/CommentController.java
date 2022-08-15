package com.ballnet.postservice.api;

import com.ballnet.postservice.models.Comment;
import com.ballnet.postservice.requests.CommentRequest;
import com.ballnet.postservice.services.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/comment")
public class CommentController {
  private final CommentService commentService;

  /**
   * Get All By Post API
   *
   * @param postId
   * @return
   */
  @GetMapping(params = "postId")
  public ResponseEntity<List<Comment>> getAllByPost(@RequestParam Long postId) {
    var result = commentService.findAllByPostId(postId);
    return ResponseEntity.ok(result);
  }

  /**
   * Get All By User API
   *
   * @param userId
   * @return
   */
  @GetMapping(params = "userId")
  public ResponseEntity<List<Comment>> getAllByUser(@RequestParam Long userId) {
    var result = commentService.findAllByUserId(userId);
    return ResponseEntity.ok(result);
  }

  /**
   * Get by id API
   *
   * @param id
   * @return
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<Comment> getById(@PathVariable Long id) {
    var result = commentService.findById(id);
    return ResponseEntity.ok(result);
  }

  /**
   * Add New Comment API
   *
   * @param commentRequest
   * @return
   */
  @PostMapping()
  public ResponseEntity<Comment> add(@RequestBody @Validated CommentRequest commentRequest) {
    var comment = Comment.builder()
        .content(commentRequest.getContent())
        .userId(commentRequest.getUserId())
        .postId(commentRequest.getPostId())
        .build();

    return ResponseEntity.ok(commentService.add(comment));
  }
}
