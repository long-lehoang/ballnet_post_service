package com.ballnet.postservice.observers.comment;

import com.ballnet.postservice.entities.CommentEntity;

public interface ICommentObserver {
  void add(CommentEntity comment);

  void update(CommentEntity comment);

  void delete(Long id);
}
