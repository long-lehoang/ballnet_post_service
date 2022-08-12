package com.ballnet.postservice.services;

import com.ballnet.postservice.entities.PostEntity;
import com.ballnet.postservice.mapper.PostMapper;
import com.ballnet.postservice.models.Post;
import com.ballnet.postservice.observers.post.IPostObserver;
import com.ballnet.postservice.repositories.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {

  private final PostRepository postRepository;
  private final List<IPostObserver> postObservers;

  /**
   * Find all by user id
   * @param userId
   * @return
   */
  public List<Post> findAllByUserId(Long userId) {
    List<PostEntity> postEntities = postRepository.findByUserId(userId);
    List<Post> posts = postEntities.stream().map(PostMapper.INSTANCE::toPost).collect(Collectors.toList());
    return posts;
  }

  /**
   * New Post
   * @param post
   * @return
   */
  public Post add(Post post) {
    log.info("New Post: {}",post);
    PostEntity postEntity = PostMapper.INSTANCE.toPostEntity(post);
    var saved = postRepository.saveAndFlush(postEntity);

    postObservers.forEach((postObserver) -> {
      postObserver.add(saved);
    });

    return PostMapper.INSTANCE.toPost(saved);
  }

  /**
   * Update Post
   * @param post
   * @return Post
   */
  public Post update(Post post) {
    log.info("Update Post: {}",post);
    PostEntity postEntity = PostMapper.INSTANCE.toPostEntity(post);
    var saved = postRepository.saveAndFlush(postEntity);

    postObservers.forEach((postObserver) -> {
      postObserver.update(saved);
    });

    return PostMapper.INSTANCE.toPost(saved);
  }

  /**
   * Delete Post By Id
   * @param id
   */
  public void delete(Long id) {
    log.info("Delete post id: {}", id);
    postRepository.deleteById(id);

    postObservers.forEach( postObserver -> {
      postObserver.delete(id);
    });
  }

  /**
   * Find one by Id
   * @param id
   * @return
   */
  public Post findOneById(Long id) {
    var post = postRepository.findById(id);

    if(post.isPresent())
      return PostMapper.INSTANCE.toPost(post.get());
    else
      throw new EmptyResultDataAccessException(1);
  }
}
