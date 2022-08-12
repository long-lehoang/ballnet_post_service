package com.ballnet.postservice.observers.post;

import com.ballnet.postservice.entities.PostEntity;

public interface IPostObserver{
  void add(PostEntity post);
  void update(PostEntity post);
  void delete(Long id);
}
