package com.ballnet.postservice.observers.post;

import com.ballnet.postservice.entities.PostEntity;
import org.springframework.stereotype.Component;

@Component
public class MailPost implements IPostObserver{
  @Override
  public void add(PostEntity post) {

  }

  @Override
  public void update(PostEntity post) {

  }

  @Override
  public void delete(Long id) {

  }
}
