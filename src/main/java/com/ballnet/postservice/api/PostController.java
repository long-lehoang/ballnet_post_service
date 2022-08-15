package com.ballnet.postservice.api;

import com.ballnet.postservice.models.Post;
import com.ballnet.postservice.requests.PostRequest;
import com.ballnet.postservice.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
@AllArgsConstructor
public class PostController {

  private final PostService postService;

  @GetMapping(value = "/test")
  public String test() {
    return "hello world";
  }

  /**
   * @param userId
   * @return
   */
  @GetMapping()
  public ResponseEntity<List<Post>> getAll(@RequestParam Long userId) {
    List<Post> posts = postService.findAllByUserId(userId);
    return ResponseEntity.ok(posts);
  }

  /**
   * @param id
   * @return
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<Post> get(@PathVariable Long id) {
    Post post = postService.findOneById(id);
    return ResponseEntity.ok(post);
  }

  /**
   * @param postRequest
   * @return
   */
  @PostMapping()
  public ResponseEntity<Post> add(@RequestBody @Validated PostRequest postRequest) {
    Post post = Post.builder()
        .content(postRequest.getContent())
        .userId(postRequest.getUserId())
        .build();

    var result = postService.add(post);
    return ResponseEntity.ok(result);
  }

  /**
   * @param postRequest
   * @return
   */
  @PutMapping(value = "/{id}")
  public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody @Validated PostRequest postRequest) {
    Post post = Post.builder()
        .content(postRequest.getContent())
        .userId(postRequest.getUserId())
        .id(id)
        .build();

    var result = postService.update(post);
    return ResponseEntity.ok(result);
  }

  /**
   * @param id
   * @return
   */
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    postService.delete(id);

    return ResponseEntity.ok(HttpStatus.OK.toString());
  }
}
