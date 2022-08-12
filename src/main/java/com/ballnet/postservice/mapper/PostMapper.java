package com.ballnet.postservice.mapper;

import com.ballnet.postservice.entities.PostEntity;
import com.ballnet.postservice.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
  static final PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

  Post toPost(PostEntity postEntity);
  PostEntity toPostEntity(Post post);
}
