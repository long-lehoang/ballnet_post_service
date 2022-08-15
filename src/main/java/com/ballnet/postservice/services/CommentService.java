package com.ballnet.postservice.services;

import com.ballnet.postservice.entities.CommentEntity;
import com.ballnet.postservice.mapper.CommentMapper;
import com.ballnet.postservice.models.Comment;
import com.ballnet.postservice.observers.comment.ICommentObserver;
import com.ballnet.postservice.repositories.ICommentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {

  private final ICommentRepository commentRepository;
  private final List<ICommentObserver> commentObservers;

  /**
   * Find All Comment By Post Id
   *
   * @param postId
   * @return
   */
  public List<Comment> findAllByPostId(Long postId) {
    List<CommentEntity> commentEntities = commentRepository.findByPostId(postId);
    return commentEntities.stream().map(CommentMapper.INSTANCE::toComment).toList();
  }

  /**
   * Find All Comment By User Id
   *
   * @param userId
   * @return
   */
  public List<Comment> findAllByUserId(Long userId) {
    List<CommentEntity> commentEntities = commentRepository.findByUserId(userId);
    return commentEntities.stream().map(CommentMapper.INSTANCE::toComment).toList();
  }

  /**
   * Find one by Id
   *
   * @param id
   * @return
   */
  public Comment findById(Long id) {
    var result = commentRepository.findById(id);

    if (result.isPresent()) {
      return CommentMapper.INSTANCE.toComment(result.get());
    } else {
      throw new EmptyResultDataAccessException(1);
    }
  }

  /**
   * Add new comment
   *
   * @param comment
   * @return
   */
  public Comment add(Comment comment) {
    log.info("New Comment: {}", comment);
    var saved = commentRepository.saveAndFlush(CommentMapper.INSTANCE.toCommentEntity(comment));

    commentObservers.forEach(observer -> observer.add(saved));

    return CommentMapper.INSTANCE.toComment(saved);
  }
}
